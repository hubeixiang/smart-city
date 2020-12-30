package com.sct.comons.ftp.autoconfigure;

import com.sct.comons.ftp.constants.FtpConstants;
import com.sct.comons.ftp.properties.FtpConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(FtpConfiguration.class)
public class FtpAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @Conditional(FtpConfigurationCondition.class)
    @Import({FtpTransferAutoConfiguration.FtpTransfer.class})
    protected static class FtpTransferSourceAutoConfiguration {

    }

    static class FtpConfigurationCondition extends AnyNestedCondition {
        FtpConfigurationCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(name = FtpConstants.FTP_CONFIGURATION_PREFX + ".enable", havingValue = "true")
        static class ExplicitSessionEnable {
        }
    }

}
