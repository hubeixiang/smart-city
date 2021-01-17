package com.sct.application.authorization.support.authentication.build;

import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.Instant;

public class CustomWebAuthenticationDetails implements Serializable {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private final String remoteAddress;
    private final String sessionId;
    private final String loginTime;

    // ~ Constructors
    // ===================================================================================================

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        this.remoteAddress = request.getRemoteAddr();

        HttpSession session = request.getSession(false);
        this.sessionId = (session != null) ? session.getId() : null;
        this.loginTime = Instant.now().toString();
    }

    /**
     * Constructor to add Jackson2 serialize/deserialize support
     *
     * @param remoteAddress remote address of current request
     * @param sessionId     session id
     */
    private CustomWebAuthenticationDetails(final String remoteAddress, final String sessionId, final String loginTime) {
        this.remoteAddress = remoteAddress;
        this.sessionId = sessionId;
        this.loginTime = loginTime;
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomWebAuthenticationDetails) {
            CustomWebAuthenticationDetails rhs = (CustomWebAuthenticationDetails) obj;

            if ((remoteAddress == null) && (rhs.getRemoteAddress() != null)) {
                return false;
            }

            if ((remoteAddress != null) && (rhs.getRemoteAddress() == null)) {
                return false;
            }

            if (remoteAddress != null) {
                if (!remoteAddress.equals(rhs.getRemoteAddress())) {
                    return false;
                }
            }

            if ((sessionId == null) && (rhs.getSessionId() != null)) {
                return false;
            }

            if ((sessionId != null) && (rhs.getSessionId() == null)) {
                return false;
            }

            if (sessionId != null) {
                if (!sessionId.equals(rhs.getSessionId())) {
                    return false;
                }
            }

            if ((loginTime == null) && (rhs.getLoginTime() != null)) {
                return false;
            }

            if ((loginTime != null) && (rhs.getLoginTime() == null)) {
                return false;
            }

            if (loginTime != null) {
                if (!loginTime.equals(rhs.getLoginTime())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Indicates the TCP/IP address the authentication request was received from.
     *
     * @return the address
     */
    public String getRemoteAddress() {
        return remoteAddress;
    }

    /**
     * Indicates the <code>HttpSession</code> id the authentication request was received
     * from.
     *
     * @return the session ID
     */
    public String getSessionId() {
        return sessionId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    @Override
    public int hashCode() {
        int code = 7654;

        if (this.remoteAddress != null) {
            code = code * (this.remoteAddress.hashCode() % 7);
        }

        if (this.sessionId != null) {
            code = code * (this.sessionId.hashCode() % 7);
        }

        if (this.loginTime != null) {
            code = code * (this.loginTime.hashCode() % 7);
        }

        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
        sb.append("SessionId: ").append(this.getSessionId());
        sb.append("LoginTime: ").append(this.getLoginTime());

        return sb.toString();
    }
}
