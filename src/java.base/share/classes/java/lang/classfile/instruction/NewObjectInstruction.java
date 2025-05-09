/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.lang.classfile.instruction;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeElement;
import java.lang.classfile.CodeModel;
import java.lang.classfile.Instruction;
import java.lang.classfile.Opcode;
import java.lang.classfile.constantpool.ClassEntry;

import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models a {@link Opcode#NEW new} instruction in the {@code code} array of a {@code Code}
 * attribute.  Delivered as a {@link CodeElement} when traversing the elements
 * of a {@link CodeModel}.
 * <p>
 * A new object instruction is composite:
 * {@snippet lang=text :
 * // @link substring="NewObjectInstruction" target="#of" :
 * NewObjectInstruction(ClassEntry className) // @link substring="className" target="#className"
 * }
 * where the {@code className} is a non-abstract class.
 *
 * @see Opcode.Kind#NEW_OBJECT
 * @see CodeBuilder#new_ CodeBuilder::new_
 * @jvms 6.5.new <em>new</em>
 * @since 24
 */
public sealed interface NewObjectInstruction extends Instruction
        permits AbstractInstruction.BoundNewObjectInstruction, AbstractInstruction.UnboundNewObjectInstruction {

    /**
     * {@return the type of object to create}
     */
    ClassEntry className();

    /**
     * {@return a new object instruction}
     *
     * @param className the type of object to create
     */
    static NewObjectInstruction of(ClassEntry className) {
        return new AbstractInstruction.UnboundNewObjectInstruction(className);
    }
}
