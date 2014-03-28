package com.tomitribe.tribestream.registry.model;

import com.tomitribe.wadl.api.ApiVersions;
import com.tomitribe.wadl.api.Categories;
import com.tomitribe.wadl.api.Rates;
import com.tomitribe.wadl.api.RolesAllowed;
import com.tomitribe.wadl.api.SeeAlso;
import com.tomitribe.wadl.api.Tags;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Should contains our additional metadata
 */
public class AbstractEnrichDto extends AbstractDto {

    protected Categories categories;
    protected SeeAlso seeAlso;
    protected Rates rates;
    protected Tags tags;
    protected ApiVersions apiVersions;
    protected RolesAllowed rolesAllowed;

    public AbstractEnrichDto() {

    }

    public AbstractEnrichDto(final List<Object> any) {
        categories = new Categories().withCategory("Default");
        extractFromAny(any);
    }

    public Categories getCategories() {
        return categories;
    }

    public SeeAlso getSeeAlso() {
        return seeAlso;
    }

    public Rates getRates() {
        return rates;
    }

    public Tags getTags() {
        return tags;
    }

    public ApiVersions getApiVersions() {
        return apiVersions;
    }

    public RolesAllowed getRolesAllowed() {
        return rolesAllowed;
    }

    public void extractFromAny(final List<Object> any) {
        for (final Object o : any) {
            if (o instanceof Categories) {
                categories = (Categories) o;

            } else if (o instanceof SeeAlso) {
                seeAlso = (SeeAlso) o;

            } else if (o instanceof Rates) {
                rates = (Rates) o;

            } else if (o instanceof Tags) {
                tags = (Tags) o;

            } else if (o instanceof ApiVersions) {
                apiVersions = (ApiVersions) o;

            } else if (o instanceof RolesAllowed) {
                rolesAllowed = (RolesAllowed) o;

            }
        }
    }

    @Override
    public String toString() {
        return "AbstractEnrichDto{" +
                "categories=" + ToStringBuilder.reflectionToString(categories, ToStringStyle.SHORT_PREFIX_STYLE) +
                ", seeAlso=" + ToStringBuilder.reflectionToString(seeAlso, ToStringStyle.SHORT_PREFIX_STYLE) +
                ", rates=" + ToStringBuilder.reflectionToString(rates, ToStringStyle.SHORT_PREFIX_STYLE) +
                ", tags=" + ToStringBuilder.reflectionToString(tags, ToStringStyle.SHORT_PREFIX_STYLE) +
                ", apiVersions=" + ToStringBuilder.reflectionToString(apiVersions, ToStringStyle.SHORT_PREFIX_STYLE) +
                ", rolesAllowed=" + ToStringBuilder.reflectionToString(rolesAllowed, ToStringStyle.SHORT_PREFIX_STYLE) +
                '}';
    }
}
