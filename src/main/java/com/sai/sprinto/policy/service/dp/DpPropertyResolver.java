package com.sai.sprinto.policy.service.dp;

import org.springframework.stereotype.Service;

@Service
public class DpPropertyResolver {

    public boolean isUserPolicyV2Enabled(){
        return false;
    }

}
