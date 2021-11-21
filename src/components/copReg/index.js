/*
 * Copyright  2018 Linkel Technology Co., Ltd, Beijing
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BA SIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import React from "react";
import {
    Create,
    ImageInput,
    ImageField,
    TextInput,
    SimpleForm,
    required,
} from "react-admin";
import CopRegCreateToolbar from "./CopRegCreateToolbar";

export { default as CopRegList } from "./CopRegList";

export { default as CopRegShow } from "./CopRegShow";

export const CopRegCreate = props => (
    <Create {...props}>
        <SimpleForm toolbar={<CopRegCreateToolbar />}>
            <ImageInput multiple source="imagesToUpload" validate={required()}>
                <ImageField source="src" title="title" />
            </ImageInput>
            <TextInput source="image.name" />
            <TextInput source="image.desc" />
            <TextInput source="password" type="password" />
        </SimpleForm>
    </Create>
);
