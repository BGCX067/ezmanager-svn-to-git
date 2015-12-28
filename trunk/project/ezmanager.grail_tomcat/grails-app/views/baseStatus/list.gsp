

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'baseStatus.label', default: 'Status')}" />
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
	            <g:form method="post" >
	                <div class="dialog">
	                   Search: <g:textField name="searchFilter" value="${searchFilter}"/>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:actionSubmit class="search" action="list" value="${message(code: 'default.button.search.label', default: 'Search')}" /></span>
					</div>	                    
	            </g:form>

                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="statusId" title="${message(code: 'baseStatus.statusId.label', default: 'Status Id')}" width="5%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'baseStatus.name.label', default: 'Name')}" params="${params}"/>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${baseStatusInstanceList}" status="i" var="baseStatusInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${baseStatusInstance.statusId}">${fieldValue(bean: baseStatusInstance, field: "statusId")}</g:link></td>
                        
                            <td>${fieldValue(bean: baseStatusInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${baseStatusInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
