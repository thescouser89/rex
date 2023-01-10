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
package org.jboss.pnc.rex.common.exceptions;

public class ConstraintConflictException extends RuntimeException {

    private final String constraint;

    public ConstraintConflictException(String constraint) {
        super();
        this.constraint = constraint;
    }

    public ConstraintConflictException(String message, String constraint) {
        super(message);
        this.constraint = constraint;
    }

    public ConstraintConflictException(String message, Throwable cause, String constraint) {
        super(message, cause);
        this.constraint = constraint;
    }

    public ConstraintConflictException(Throwable cause, String constraint) {
        super(cause);
        this.constraint = constraint;
    }

    public String getConstraint() {
        return constraint;
    }

}
