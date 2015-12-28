

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
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
                        
                            <g:sortableColumn property="addressId" title="${message(code: 'address.addressId.label', default: 'Address Id')}" />
                        
                            <g:sortableColumn property="streetNumber" title="${message(code: 'address.streetNumber.label', default: 'Street Number')}" />

                            <g:sortableColumn property="streetIdStreet" title="${message(code: 'address.streetIdStreet.label', default: 'Street')}" />
                        
                            <g:sortableColumn property="remark" title="${message(code: 'address.remark.label', default: 'Remark')}" />
                        
                            <g:sortableColumn property="zip" title="${message(code: 'address.zip.label', default: 'Zip')}" />
                        
                            <g:sortableColumn property="bActive" title="${message(code: 'address.bActive.label', default: 'BA ctive')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${addressInstanceList}" status="i" var="addressInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${addressInstance.addressId}">${fieldValue(bean: addressInstance, field: "addressId")}</g:link></td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "streetNumber")}</td>

                            <td>${fieldValue(bean: addressInstance, field: "streetIdStreet.name")}</td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "remark")}</td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "zip")}</td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "bActive")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${addressInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
