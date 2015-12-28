

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
               <g:form method="post" >
                   <div class="dialog">
                      <table>
                         <tr>
                            <td>Street number:</td>
                            <td><g:textField name="streetNumber" value="${streetNumber}"/></td>
                         </tr>
                         <tr>
                            <td>Street:</td>
                            <td><g:textField name="streetName" value="${streetName}"/></td>
                         </tr>
						 <tr>
						 	<td>Zip:</td>
						 	<td><g:textField name="zip" value="${zip}"/></td>
						 </tr>
						 <tr>
						 	<td>City:</td>
						 	<td><g:textField name="cityName" value="${cityName}"/></td>
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
                        
                            <g:sortableColumn property="addressId" title="${message(code: 'address.addressId.label', default: 'Address Id')}" width="7%" params="${params}"/>
                        
                            <g:sortableColumn property="streetNumber" title="${message(code: 'address.streetNumber.label', default: 'Street Number')}" width="7%" params="${params}"/>

                            <g:sortableColumn property="street" title="${message(code: 'address.street.label', default: 'Street')}" params="${params}"/>
                        
                            <g:sortableColumn property="zip" title="${message(code: 'address.zip.label', default: 'Zip')}" params="${params}"/>
                            
                            <g:sortableColumn property="city" title="${message(code: 'address.street.city.label', default: 'City')}" params="${params}"/>
                        
                            <g:sortableColumn property="bActive" title="${message(code: 'address.bActive.label', default: 'Is active')}" params="${params}"/>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${addressInstanceList}" status="i" var="addressInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${addressInstance.addressId}">${fieldValue(bean: addressInstance, field: "addressId")}</g:link></td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "streetNumber")}</td>

                            <td>${fieldValue(bean: addressInstance, field: "street.name")}</td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "zip")}</td>
                            
                            <td>${fieldValue(bean: addressInstance, field: "street.city.name")}</td>
                        
                            <td>${fieldValue(bean: addressInstance, field: "bActive")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${addressInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
