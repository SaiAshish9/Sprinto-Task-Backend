package com.sai.sprinto.policy.service.policy.abstractFactory.service;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractUserPolicyService<T extends UserPolicyContext> implements UserPolicyService<T> {

    @Override
    public void process(T context){
        populateApprovedPolicies(context);
        populateAcknowledgedPolicyEntities(context);
        buildUserPolicies(context);
        buildCustomerTemplatesAndSelectedPolicyIds(context);
        postProcess(context);
    }

    abstract protected void populateApprovedPolicies(T context);
    abstract protected void populateAcknowledgedPolicyEntities(T context);
    abstract protected void buildCustomerTemplatesAndSelectedPolicyIds(T context);

    private void buildUserPolicies(T context){

    }

    private void postProcess(T context){

    }

}
