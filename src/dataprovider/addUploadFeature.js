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
const convertFile = (file, targetType) =>
    new Promise((resolve, reject) => {
        const reader = new FileReader();
        switch(targetType){
            case 'base64':
                reader.readAsDataURL(file.rawFile);
                break;
            case 'utf8':
                reader.readAsText(file.rawFile);
                break;
            default:
                reader.readAsArrayBuffer(file.rawFile);
        }

        reader.onload = () => {
            resolve({
                title:file.title,
                src:reader.result
            });
        }
        reader.onerror = reject;
    });

const addUploadCapabilities = requestHandler => (type, resource, params) => {
    if ((type === 'UPDATE'|| type === 'CREATE') && (resource === 'keypairs' )) {
        if(params.data.keypairFile){
            return convertFile(params.data.keypairFile, 'utf8')
            .then(keypair =>
                requestHandler(type, resource, {
                    data: {
                        keypairImported: keypair,
                        ownerID: params.data.ownerID,
                        name: params.data.name
                    }
                })
            )
        }

        return requestHandler(type, resource, params);
    }

    return requestHandler(type, resource, params);
};

export default addUploadCapabilities;
