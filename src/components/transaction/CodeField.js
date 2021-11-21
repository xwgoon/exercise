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
import React from 'react'
import get from 'lodash/get'
import PropTypes from 'prop-types'
import { Light as SyntaxHighlighter } from "react-syntax-highlighter";
import googlecode from "react-syntax-highlighter/dist/esm/styles/hljs/googlecode";
import { ExpansionPanel, ExpansionPanelSummary, ExpansionPanelDetails, Typography} from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const CodeField = ({source, record={}, language, expansionSummary}) => 
    <ExpansionPanel>
        <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />} >
            <Typography variant="caption" style={{flexBasis: "33.33%"}}>{expansionSummary[0]}</Typography>
            <Typography variant="caption" style={{flexBasis: "33.33%"}}>{expansionSummary[1]}</Typography>
            <Typography variant="caption" >{expansionSummary[2]}</Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails>
            <SyntaxHighlighter 
                language={language}
                style={googlecode}
                showLineNumbers
                customStyle={{ overflowY: "auto", maxHeight: "50rem" }}
            >
                {get(record, source)}
            </SyntaxHighlighter>
        </ExpansionPanelDetails>
    </ExpansionPanel>

CodeField.propTypes = {
    label: PropTypes.string,
    record: PropTypes.object,
    source: PropTypes.string.isRequired,
    language: PropTypes.string,
    expansionSummary: PropTypes.array,
}

CodeField.defaultProps = {addLabel: true}

export default CodeField