

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'state.label', default: 'State')}" />
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
                        
                            <g:sortableColumn property="stateId" title="${message(code: 'state.stateId.label', default: 'State Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'state.name.label', default: 'Name')}" />
                        
                            <th><g:message code="state.countryIdCountry.label" default="Country" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${stateInstanceList}" status="i" var="stateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${stateInstance.stateId}">${fieldValue(bean: stateInstance, field: "stateId")}</g:link></td>
                        
                            <td>${fieldValue(bean: stateInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: stateInstance, field: "countryIdCountry.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${stateInstanceTotal}" />
            </div>
        </div>
    </body>
</html>