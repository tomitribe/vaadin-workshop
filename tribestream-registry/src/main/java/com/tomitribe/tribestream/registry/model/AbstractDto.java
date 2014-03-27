package com.tomitribe.tribestream.registry.model;

import com.tomitribe.wadl.api.Doc;
import com.tomitribe.wadl.api.Method;
import com.tomitribe.wadl.api.Param;

import java.util.List;

public class AbstractDto {

    public String getStringDoc(final Method method) {
        return getStringDoc(method.getDoc());
    }

    public String getStringDoc(final Param documented) {
        return getStringDoc(documented.getDoc());
    }

    public String getStringDoc(final List<Doc> docList) {
        final StringBuilder documentation = new StringBuilder();

        if (docList != null) {

            for (final Doc doc : docList) {
                final List<Object> contentList = doc.getContent();
                for (final Object content : contentList) {
                    documentation.append(content.toString());
                }
            }
        }

        return documentation.toString();
    }
}
