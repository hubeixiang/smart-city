package com.sct.comons.ftp.autoconfigure;

import com.sct.comons.ftp.properties.FtpConfiguration;
import com.sct.comons.ftp.service.AgentFtpTransferFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

abstract class FtpTransferAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    static class FtpTransfer {
        @Bean
        public AgentFtpTransferFactory agentFtpTransferFactory(FtpConfiguration ftpConfiguration) {
            return new AgentFtpTransferFactory();
        }
    }

}
