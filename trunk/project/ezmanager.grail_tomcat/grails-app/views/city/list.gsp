<%@page import="ezmanager.*"%>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'city.label', default: 'City')}" />
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
                            <td>City:</td>
                            <td><g:textField name="name" value="${name}"/></td>
                         </tr>
						 <tr>
						 	<td>State:</td>
						 	<td><g:select name="stateId" value="${stateId}" from="${State.listOrderByName()}" optionKey="stateId" optionValue="name" noSelection="['':'All']"/></td>
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
                        
                            <g:sortableColumn property="cityId" title="${message(code: 'city.cityId.label', default: 'City Id')}" width="7%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'city.name.label', default: 'Name')}" params="${params}"/>
                            
                            <th><g:message code="city.state.label" default="State" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cityInstanceList}" status="i" var="cityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cityInstance.cityId}">${fieldValue(bean: cityInstance, field: "cityId")}</g:link></td>
                        
                            <td>${fieldValue(bean: cityInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: cityInstance, field: "state.name")}</td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cityInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
