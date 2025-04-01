package com.sai.sprinto.policy.service.policy.abstractFactory.service;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractUserPolicyService<T extends UserPolicyContext> implements UserPolicyService<T> {

    @Override
    public void process(T userPolicyContext){
        populateApprovedPolicies();
        populateAcknowledgedPolicyEntities();
        buildUserPolicies();
        buildCustomerTemplatesAndSelectedPolicyIds();
        postProcess();
    }

    abstract protected void populateApprovedPolicies();
    abstract protected void populateAcknowledgedPolicyEntities();
    abstract protected void buildCustomerTemplatesAndSelectedPolicyIds();

    private void buildUserPolicies(){

    }

    private void postProcess(){

    }

}
