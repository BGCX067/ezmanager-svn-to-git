<%@page import="ezmanager.*"%>
 
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
               <g:form method="post" >
                   <div class="dialog">
                      <table>
                         <tr>
                            <td>State:</td>
                            <td><g:textField name="name" value="${name}"/></td>
                         </tr>
						 <tr>
						 	<td>Country:</td>
						 	<td><g:select name="countryId" value="${countryId}" from="${Country.listOrderByName()}" optionKey="countryId" optionValue="name" noSelection="['':'All']"/></td>
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
                        
                            <g:sortableColumn property="stateId" title="${message(code: 'state.stateId.label', default: 'State Id')}" width="7%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'state.name.label', default: 'Name')}" params="${params}"/>
                        
                            <th><g:message code="state.country.label" default="Country" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${stateInstanceList}" status="i" var="stateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${stateInstance.stateId}">${fieldValue(bean: stateInstance, field: "stateId")}</g:link></td>
                        
                            <td>${fieldValue(bean: stateInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: stateInstance, field: "country.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${stateInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
