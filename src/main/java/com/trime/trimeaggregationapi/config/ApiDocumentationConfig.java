package com.trime.trimeaggregationapi.config;

import io.swagger.annotations.*;

@SwaggerDefinition(
        info = @Info(
                description = "Trimi aggregation Resources",
                version = "V1.0",
                title = "Trimi aggregation Resource API",
                contact = @Contact(
                        name = "harshitha de silva",
                        url = "http://www.trimiservices.com"
                )/*,
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )*/
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = " ")
)
public interface ApiDocumentationConfig {

}