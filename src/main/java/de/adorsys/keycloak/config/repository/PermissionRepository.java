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

import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.representations.idm.ManagementPermissionRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionRepository {

    private final ClientRepository clientRepository;

    @Autowired
    public PermissionRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void enablePermission(String realmName, String id) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);

        clientResource.setPermissions(new ManagementPermissionRepresentation(true));
    }

    public boolean isPermissionEnabled(String realmName, String id) {
        ClientResource clientResource = clientRepository.getResourceById(realmName, id);

        return clientResource.getPermissions().isEnabled();
    }
}
