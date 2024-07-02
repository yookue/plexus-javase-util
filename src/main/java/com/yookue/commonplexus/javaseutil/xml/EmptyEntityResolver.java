package com.yookue.commonplexus.javaseutil.xml;


import java.io.ByteArrayInputStream;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;


/**
 * {@link org.xml.sax.EntityResolver} with empty stream
 *
 * @author David Hsing
 * @reference "http://www.jdom.org/docs/faq.html#a0350"
 */
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public class EmptyEntityResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(@Nullable String publicId, @Nullable String systemId) {
        return new InputSource(new ByteArrayInputStream(ArrayUtils.EMPTY_BYTE_ARRAY));
    }
}
