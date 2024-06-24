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
 * Enumerations of security algorithm types
 *
 * @author David Hsing
 * @reference "https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#securerandom-number-generation-algorithms"
 * @see java.security.SecureRandom
 * @see "sun.security.jca.ProviderList"
 * @see "io.jsonwebtoken.SignatureAlgorithm"
 */
@AllArgsConstructor
@Getter
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public enum SecurityAlgorithmType implements ValueEnum<String> {
    MD5("MD5"),                                          // $NON-NLS-1$
    SHA1("SHA1"),                                        // $NON-NLS-1$
    SHA1_PRNG("SHA1PRNG"),                               // $NON-NLS-1$
    SHA224("SHA-224"),                                   // $NON-NLS-1$
    SHA256("SHA-256"),                                   // $NON-NLS-1$
    SHA384("SHA-384"),                                   // $NON-NLS-1$
    SHA512("SHA-512"),                                   // $NON-NLS-1$
    SHA512_224("SHA-512/224"),                           // $NON-NLS-1$
    SHA512_256("SHA-512/256"),                           // $NON-NLS-1$
    HMAC_SHA224("HmacSHA224"),                           // $NON-NLS-1$
    HMAC_SHA256("HmacSHA256"),                           // $NON-NLS-1$
    HMAC_SHA384("HmacSHA384"),                           // $NON-NLS-1$
    HMAC_SHA512("HmacSHA512"),                           // $NON-NLS-1$
    SHA224_WITH_RSA("SHA224withRSA"),                    // $NON-NLS-1$
    SHA256_WITH_RSA("SHA256withRSA"),                    // $NON-NLS-1$
    SHA384_WITH_RSA("SHA384withRSA"),                    // $NON-NLS-1$
    SHA512_WITH_RSA("SHA512withRSA"),                    // $NON-NLS-1$
    SHA224_WITH_DSA("SHA224withDSA"),                    // $NON-NLS-1$
    SHA256_WITH_DSA("SHA256withDSA"),                    // $NON-NLS-1$
    SHA384_WITH_DSA("SHA384withDSA"),                    // $NON-NLS-1$
    SHA512_WITH_DSA("SHA512withDSA"),                    // $NON-NLS-1$
    SHA224_WITH_ECDSA("SHA224withECDSA"),                // $NON-NLS-1$
    SHA256_WITH_ECDSA("SHA256withECDSA"),                // $NON-NLS-1$
    SHA384_WITH_ECDSA("SHA384withECDSA"),                // $NON-NLS-1$
    SHA512_WITH_ECDSA("SHA512withECDSA"),                // $NON-NLS-1$
    SHA3_224("SHA3-224"),                                // $NON-NLS-1$
    SHA3_256("SHA3-256"),                                // $NON-NLS-1$
    SHA3_384("SHA3-384"),                                // $NON-NLS-1$
    SHA3_512("SHA3-512"),                                // $NON-NLS-1$
    HMAC_SHA3_224("HmacSHA3-224"),                       // $NON-NLS-1$
    HMAC_SHA3_256("HmacSHA3-256"),                       // $NON-NLS-1$
    HMAC_SHA3_384("HmacSHA3-384"),                       // $NON-NLS-1$
    HMAC_SHA3_512("HmacSHA3-512"),                       // $NON-NLS-1$
    SHA256_WITH_RSA_AND_MGF1("SHA256withRSAandMGF1"),    // $NON-NLS-1$
    SHA384_WITH_RSA_AND_MGF1("SHA384withRSAandMGF1"),    // $NON-NLS-1$
    SHA512_WITH_RSA_AND_MGF1("SHA512withRSAandMGF1");    // $NON-NLS-1$

    private final String value;
}
