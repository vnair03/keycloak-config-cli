/*-
 * ---license-start
 * keycloak-config-cli
 * ---
 * Copyright (C) 2017 - 2021 adorsys GmbH & Co. KG @ https://adorsys.com
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ---license-end
 */

package de.adorsys.keycloak.config.service.rolecomposites.client;


public class ClientUpdateConfig {
    private String realmName;
    private String roleClientId;
    private String roleName;
    private String clientId;
    //private List<String> composites;

    //getters

    public String getRealmName() {
        return realmName;
    }

    public String getRoleClientId() {
        return roleClientId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getClientId() {
        return clientId;
    }

    //setters


    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public void setRoleClientId(String roleClientId) {
        this.roleClientId = roleClientId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ClientUpdateConfig(String realmName, String roleClientId, String roleName, String clientId) {
        this.realmName = realmName;
        this.roleClientId = roleClientId;
        this.roleName = roleName;
        this.clientId = clientId;
    }
}
