<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Swagger UI</title>
    <link rel="icon" type="image/png" href="<%= assetPath(src: 'jaxrs-swagger-ui/images/favicon-32x32.png') %>" sizes="32x32" />
    <link rel="icon" type="image/png" href="<%= assetPath(src: 'jaxrs-swagger-ui/images/favicon-16x16.png') %>" sizes="16x16" />
    <asset:stylesheet src="jaxrs-swagger-ui/css/typography.css" media='screen'/>
    <asset:stylesheet src="jaxrs-swagger-ui/css/reset.css" media='screen'/>
    <asset:stylesheet src="jaxrs-swagger-ui/css/screen.css" media='screen'/>
    <asset:stylesheet src="jaxrs-swagger-ui/css/reset.css" media='print'/>
    <asset:stylesheet src="jaxrs-swagger-ui/css/print.css" media='print'/>

    <asset:javascript src="jaxrs-swagger-ui/lib/object-assign-pollyfill.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/jquery-1.8.0.min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/jquery.slideto.min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/jquery.wiggle.min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/jquery.ba-bbq.min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/handlebars-4.0.5.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/lodash.min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/backbone-min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/swagger-ui.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/highlight.9.1.0.pack.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/highlight.9.1.0.pack_extended.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/jsoneditor.min.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/marked.js"/>
    <asset:javascript src="jaxrs-swagger-ui/lib/swagger-oauth.js"/>

    <!-- Some basic translations -->
    <!-- <script src='lang/translator.js' type='text/javascript'></script> -->
    <!-- <script src='lang/ru.js' type='text/javascript'></script> -->
    <!-- <script src='lang/en.js' type='text/javascript'></script> -->

    <script type="text/javascript">
        $(function () {
            /*
            var url = window.location.search.match(/url=([^&]+)/);
            if (url && url.length > 1) {
                url = decodeURIComponent(url[1]);
            } else {
                url = "http://petstore.swagger.io/v2/swagger.json";
            }
            */
            var url = "<%= createLink(uri: '/swagger.json') %>";

            hljs.configure({
                highlightSizeThreshold: 5000
            });

            // Pre load translate...
            if(window.SwaggerTranslator) {
                window.SwaggerTranslator.translate();
            }
            window.swaggerUi = new SwaggerUi({
                url: url,
                dom_id: "swagger-ui-container",
                supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
                onComplete: function(swaggerApi, swaggerUi){
                    if(typeof initOAuth == "function") {
                        initOAuth({
                            clientId: "your-client-id",
                            clientSecret: "your-client-secret-if-required",
                            realm: "your-realms",
                            appName: "your-app-name",
                            scopeSeparator: " ",
                            additionalQueryStringParams: {}
                        });
                    }

                    if(window.SwaggerTranslator) {
                        window.SwaggerTranslator.translate();
                    }
                },
                onFailure: function(data) {
                    log("Unable to Load SwaggerUI");
                },
                docExpansion: "none",
                jsonEditor: false,
                defaultModelRendering: 'schema',
                showRequestHeaders: false
            });

            window.swaggerUi.load();

            function log() {
                if ('console' in window) {
                    console.log.apply(console, arguments);
                }
            }
        });
    </script>
</head>

<body class="swagger-section">
<div id='header'>
    <div class="swagger-ui-wrap">
        <a id="logo" href="http://swagger.io"><asset:image class="logo__img" alt="swagger" height="30" width="30" src="jaxrs-swagger-ui/images/logo_small.png"/><span class="logo__title">swagger</span></a>
        <form id='api_selector'>
            <div id='auth_container'></div>
        </form>
    </div>
</div>

<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
