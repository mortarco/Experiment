/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.denasu.sample.jetty.service;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.denasu.sample.db.DBAccessor;

@Component
public class HelloWorldService {

	public String getHelloMessage()
	{

		return "Test Message";
	}

	public String getFaultMessage(String id, String password, String userInput) throws SQLException
	{
		// SQL injection
		DBAccessor accessor = new DBAccessor();
		accessor.open("localhost", "3306", "test", id, password);
		accessor.executeSQL("select foo from t where name=" + userInput);
		
		// not close
		
		// Buffer overflow
		String[] fault = new String[] {"1"};
		return fault[5];
	}
}
