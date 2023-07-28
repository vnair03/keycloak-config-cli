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

package de.adorsys.keycloak.config.repository;



import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.representations.idm.authorization.PolicyRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ResourceServerRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.ws.rs.core.Response;




public class AuthorizationRepository {

    private final ClientRepository clientRepository;

    @Autowired
    public AuthorizationRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void updateAuthorizationSettings(String realmName, String id, ResourceServerRepresentation authorizationSettings) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().update(authorizationSettings);
    }

    public void createAuthorizationResource(String realmName, String id, ResourceRepresentation resource) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);

        try (Response response = clientResource.authorization().resources().create(resource)) {
            CreatedResponseUtil.getCreatedId(response);
        }
    }

    public void updateAuthorizationResource(String realmName, String id, ResourceRepresentation resource) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().resources().resource(resource.getId()).update(resource);
    }

    public void removeAuthorizationResource(String realmName, String id, String resourceId) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().resources().resource(resourceId).remove();
    }

    public void addAuthorizationScope(String realmName, String id, String name) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);

        try (Response response = clientResource.authorization().scopes().create(new ScopeRepresentation(name))) {
            CreatedResponseUtil.getCreatedId(response);
        }
    }

    public void updateAuthorizationScope(String realmName, String id, ScopeRepresentation scope) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().scopes().scope(scope.getId()).update(scope);
    }

    public void removeAuthorizationScope(String realmName, String id, String scopeId) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().scopes().scope(scopeId).remove();
    }

    public void createAuthorizationPolicy(String realmName, String id, PolicyRepresentation policy) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);

        try (Response response = clientResource.authorization().policies().create(policy)) {
            CreatedResponseUtil.getCreatedId(response);
        }
    }

    public void updateAuthorizationPolicy(String realmName, String id, PolicyRepresentation policy) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().policies().policy(policy.getId()).update(policy);
    }

    public void removeAuthorizationPolicy(String realmName, String id, String policyId) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);
        clientResource.authorization().policies().policy(policyId).remove();
    }

    public ResourceServerRepresentation getAuthorizationConfigById(String realmName, String id) {
        return clientRepository.getResourceById(realmName, id).authorization().exportSettings();
    }

}
