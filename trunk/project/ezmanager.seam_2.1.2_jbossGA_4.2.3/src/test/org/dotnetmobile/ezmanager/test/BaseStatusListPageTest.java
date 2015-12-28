/*  <EzManager: Web application for activities follow up. As main features, you can create projects, then associate tasks and calculate their corresponding invoices. Each project is associated to one or more customers identified by their addressee>
    Copyright (C) <2010>  <Michel Petrovic> <email: dotnetmobile@gmail.com>

    Contributor(s): _____________________.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.dotnetmobile.ezmanager.test;

import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

public class BaseStatusListPageTest extends SeamTest {

	@Test
	public void testConnection() throws Exception {
		new FacesRequest("/login.xhtml") {
			protected void updateModelValues() throws Exception {
				setValue("#{credentials.username}", "ezmanager");
				setValue("#{credentials.password}", "ezmanager");
			}	

			protected void invokeApplication() throws Exception {
				invokeMethod("#{identity.login}");
			}
		}.run();
/*		
		new NonFacesRequest("/BaseStatusList.xhtml") {
			protected void renderResponse() throws Exception {
			assert (Boolean) getValue("#{baseStatusList.resultList.size() eq 0}");
			}
		}.run();
*/
	}
}
