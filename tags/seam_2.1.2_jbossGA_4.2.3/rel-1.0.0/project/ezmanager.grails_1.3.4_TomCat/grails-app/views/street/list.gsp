

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'street.label', default: 'Street')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="streetId" title="${message(code: 'street.streetId.label', default: 'Street Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'street.name.label', default: 'Name')}" />
                        
                            <th><g:message code="street.cityIdCity.label" default="City" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${streetInstanceList}" status="i" var="streetInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${streetInstance.streetId}">${fieldValue(bean: streetInstance, field: "streetId")}</g:link></td>
                        
                            <td>${fieldValue(bean: streetInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: streetInstance, field: "cityIdCity.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${streetInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
