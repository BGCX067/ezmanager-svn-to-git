

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cost.label', default: 'Cost')}" />
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
                      <table>
                         <tr>
                            <td>Cost:</td>
                            <td><g:textField name="name" value="${name}"/></td>
                         </tr>
                         <tr>
                            <td>Amount:</td>
                            <td>Min: <g:textField name="amountMin" value="${amountMin}"/></td>
                            <td>Max: <g:textField name="amountMax" value="${amountMax}"/></td>
                         </tr>
						 <tr>
						 	<td>Hours:</td>
						 	<td>Min: <g:textField name="hourMin" value="${hourMin}"/></td>
						 	<td>Max: <g:textField name="hourMax" value="${hourMax}"/></td>
						 </tr>
                      </table>
                   </div>
                   <div class="buttons">
                      <span class="button"><g:actionSubmit class="search" action="list" value="${message(code: 'default.button.search.label', default: 'Search')}" /></span>
                   </div>   
               </g:form>
            
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="costId" title="${message(code: 'cost.costId.label', default: 'Cost Id')}" width="7%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'cost.name.label', default: 'Name')}" params="${params}"/>
                        
                            <g:sortableColumn property="amountProposed" title="${message(code: 'cost.amountProposed.label', default: 'Amount Proposed')}" params="${params}"/>
                        
                            <g:sortableColumn property="totalHours" title="${message(code: 'cost.totalHours.label', default: 'Total Hours')}" params="${params}"/>
                        
                            <th><g:message code="cost.task.label" default="Task" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${costInstanceList}" status="i" var="costInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${costInstance.costId}">${fieldValue(bean: costInstance, field: "costId")}</g:link></td>
                        
                            <td>${fieldValue(bean: costInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: costInstance, field: "amountProposed")}</td>
                        
                            <td>${fieldValue(bean: costInstance, field: "totalHours")}</td>
                        
                            <td>${fieldValue(bean: costInstance, field: "task.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${costInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
