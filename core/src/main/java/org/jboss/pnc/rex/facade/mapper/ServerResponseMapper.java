/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2021-2021 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.rex.facade.mapper;

import org.jboss.pnc.rex.dto.ServerResponseDTO;
import org.jboss.pnc.rex.model.ServerResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface ServerResponseMapper extends EntityMapper<ServerResponseDTO, ServerResponse> {

    @Override
    @BeanMapping(ignoreUnmappedSourceProperties = { "byteBody", "negative" })
    ServerResponseDTO toDTO(ServerResponse dbEntity);

    @Override
    ServerResponse toDB(ServerResponseDTO dtoEntity);
}
