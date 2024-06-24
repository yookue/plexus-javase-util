/*
 * Copyright (c) 2016 Yookue Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yookue.commonplexus.javaseutil.enumeration;


import com.yookue.commonplexus.javaseutil.support.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enumerations of network protocol types in session layer
 *
 * @author David Hsing
 * @reference "https://www.educba.com/types-of-networking-protocols/"
 * @reference "https://blog.csdn.net/weixin_39218743/article/details/88818069"
 * @see "com.sun.deploy.net.protocol.ProtocolType"
 */
@AllArgsConstructor
@Getter
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public enum InetProtocolType implements ValueEnum<String> {
    FTP("ftp"),    // $NON-NLS-1$
    HTTP("http"),    // $NON-NLS-1$
    HTTPS("https"),    // $NON-NLS-1$
    IMAP("imap"),    // $NON-NLS-1$
    NFS("nfs"),    // $NON-NLS-1$
    PAP("pap"),    // $NON-NLS-1$
    POP3("pop3"),    // $NON-NLS-1$
    RPC("rpc"),    // $NON-NLS-1$
    SCP("scp"),    // $NON-NLS-1$
    SFTP("sftp"),    // $NON-NLS-1$
    SMTP("smtp"),    // $NON-NLS-1$
    SSH("ssh"),    // $NON-NLS-1$
    TELNET("telnet"),    // $NON-NLS-1$
    TFTP("tftp"),    // $NON-NLS-1$
    TLS("tls");    // $NON-NLS-1$

    private final String value;
}
