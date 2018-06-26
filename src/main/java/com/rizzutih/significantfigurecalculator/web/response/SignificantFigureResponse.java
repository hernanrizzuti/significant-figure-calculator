package com.rizzutih.significantfigurecalculator.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by h.rizzuti on 25/06/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder =SignificantFigureResponse.Builder.class)
public class SignificantFigureResponse {

    private String result;

    private String significantFigure;

    private String originalNumber;


    public String getResult() {
        return result;
    }

    public String getSignificantFigure() {
        return significantFigure;
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

    public SignificantFigureResponse() {
    }

    private SignificantFigureResponse(Builder builder) {
        result = builder.result;
        significantFigure = builder.significantFigure;
        originalNumber = builder.originalNumber;
    }

    public static Builder newBuilder() { return new Builder(); }

    public static final class Builder {
        private String result;
        private String significantFigure;
        private String originalNumber;

        private Builder() {
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public Builder withSignificantFigure(String significantFigure) {
            this.significantFigure = significantFigure;
            return this;
        }

        public Builder withOriginalNumber(String originalNumber) {
            this.originalNumber = originalNumber;
            return this;
        }

        public SignificantFigureResponse build() {
            return new SignificantFigureResponse(this);
        }
    }
}
