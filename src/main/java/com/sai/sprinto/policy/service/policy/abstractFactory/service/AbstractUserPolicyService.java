package com.sai.sprinto.policy.service.policy.abstractFactory.service;

import com.sai.sprinto.policy.builder.policy.UserPolicyBuilder;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import com.sai.sprinto.policy.enums.acknowledgement.AcknowledgementType;
import com.sai.sprinto.policy.enums.user.Role;
import com.sai.sprinto.policy.models.user.UserPolicy;
import com.sai.sprinto.policy.repository.acknowledgement.AcknowledgementRepository;
import com.sai.sprinto.policy.repository.customer.CustomerTemplateRepository;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractUserPolicyService<T extends UserPolicyContext> implements UserPolicyService<T> {

    private AcknowledgementRepository acknowledgementRepository;
    private CustomerTemplateRepository customerTemplateRepository;

    @Autowired
    public final void injectDependencies(AcknowledgementRepository acknowledgementRepository,
                                         CustomerTemplateRepository customerTemplateRepository
    ) {
        this.acknowledgementRepository = acknowledgementRepository;
        this.customerTemplateRepository = customerTemplateRepository;
    }

    @Override
    public void process(T context) {
        populateApprovedPolicies(context);
        populateAcknowledgedPolicyEntities(context);
        buildUserPolicies(context);
        buildCustomerTemplatesAndSelectedPolicyIds(context);
        postProcess(context);
    }

    abstract protected void populateApprovedPolicies(T context);

    private void populateAcknowledgedPolicyEntities(T context) {
        List<String> acknowledgedPolicyIds = new ArrayList<>();
        Map<String, AcknowledgementType> acknowledgementTypeMap = new HashMap<>();
        Role role = context.getRole();
        String userId = context.getUserId();

        if (role != Role.ADMIN) {
            List<Acknowledgement> acknowledgements = acknowledgementRepository.findByUserId(userId);
            acknowledgedPolicyIds = acknowledgements
                    .stream()
                    .map(acknowledgement -> acknowledgement.getPolicyId())
                    .toList();
            for (Acknowledgement acknowledgement : acknowledgements) {
                acknowledgementTypeMap.put(acknowledgement.getPolicyId(), acknowledgement.getType());
            }
        }
        context.setAcknowledgedPolicyIds(acknowledgedPolicyIds);
        context.setAcknowledgementTypeMap(acknowledgementTypeMap);
    }

    private void buildCustomerTemplatesAndSelectedPolicyIds(T context) {
        String userId = context.getUserId();
        List<CustomerTemplate> customerTemplates = customerTemplateRepository.findByUserId(userId);
        List<String> selectedPolicyIds = customerTemplates
                .stream()
                .map(acknowledgement -> acknowledgement.getPolicyId())
                .toList();
        context.setSelectedPolicyIds(selectedPolicyIds);
        context.setCustomerTemplates(customerTemplates);
    }

    private void buildUserPolicies(T context) {
        List<Policy> approvedPolicies = context.getApprovedPolicies();
        List<String> acknowledgedPolicyIds = context.getAcknowledgedPolicyIds();
        Map<String, AcknowledgementType> acknowledgementTypeMap = context.getAcknowledgementTypeMap();

        List<UserPolicy> userPolicies = UserPolicyBuilder
                .createPolicies(approvedPolicies, acknowledgedPolicyIds, acknowledgementTypeMap);
        context.setUserPolicies(userPolicies);
    }

    private void postProcess(T context) {
        List<UserPolicy> userPolicies = context.getUserPolicies();
        List<CustomerTemplate> customerTemplates = context.getCustomerTemplates();
        List<String> selectedPolicyIds = context.getSelectedPolicyIds();

        for (UserPolicy userPolicy : userPolicies) {
            double maxVersion = customerTemplates
                    .stream()
                    .filter(template -> template.getPolicyId().equals(userPolicy.getId()))
                    .map(CustomerTemplate::getVersion)
                    .max(Double::compareTo)
                    .orElse(1.0);
            if (selectedPolicyIds.contains(userPolicy.getId())) {
                userPolicy.setSelectedByCustomer(true);
            }
            if (maxVersion > userPolicy.getVersion()) {
                userPolicy.setUpgradeRequired(true);
            }
        }

        context.setUserPolicies(userPolicies);
    }

}
