/*
 * Copyright (c) 2011 Intuit, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * Contributors:
 *
 *    Intuit Partner Platform - initial contribution 
 *
 */

package com.app.intuit.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;

/*
 * A utility class for this sample web app.
 */

public class WebUtils {

	public static final Logger LOG = Logger.getLogger(WebUtils.class);

	public static Properties propConfig = null;
	public static String PROP_FILE = "ia.properties";

	public static String APP_TOKEN;
	public static String OAUTH_CONSUMER_KEY;
	public static String OAUTH_CONSUMER_SECRET;

	public static String OPENID_PROVIDER_URL;
	public static String OAUTH_URL;
	public static String APPCENTER_URL;

	public static String APPCENTER_URL_VARIABLE = "appcenter_url";

	public static String OPENID_RETURN_URL;
	public static String OAUTH_CALLBACK_URL;

	public static String APP_URL;

	public static String APP_URL_VARIABLE = "app_url";
	public static String USER_DETAILS = "user_details";
	public static String TENANT_ID = "TENANT_ID";
	public static String LOGGED_IN_USER = "LoggedInUser";

	public static String REPOSITORY_BASE = "";
	
	public static String STRIPE_APP_KEY; 
	
	public static String URBAN_AIRSHIP_APP_KEY; 
	public static String URBAN_AIRSHIP_APP_SECRET; 
	
	public static String AMAZON_S3_SECRET_KEY;
	public static String AMAZON_S3_BUCKET_NAME;
	public static String AMAZON_S3_KEY_ID;
	
	public static String ACS_APP_KEY;

	static {
		try {
			propConfig = PropertiesLoaderUtils.loadProperties(new ClassPathResource(PROP_FILE));

			APP_TOKEN = propConfig.getProperty("appToken");
			OAUTH_CONSUMER_KEY = propConfig.getProperty("oauth_consumer_key");
			OAUTH_CONSUMER_SECRET = propConfig.getProperty("oauth_consumer_secret");

			OPENID_PROVIDER_URL = propConfig.getProperty("openid_provider_url");
			OAUTH_URL = propConfig.getProperty("oauth_url");
			APPCENTER_URL = propConfig.getProperty("appcenter_url");

			OPENID_RETURN_URL = propConfig.getProperty("openid_return_url");
			OAUTH_CALLBACK_URL = propConfig.getProperty("oauth_callback_url");
			
			APP_URL = propConfig.getProperty("app_url");
			REPOSITORY_BASE = propConfig.getProperty("image_repository_base");
			
			STRIPE_APP_KEY = propConfig.getProperty("stripe_app_key");
			
			URBAN_AIRSHIP_APP_KEY = propConfig.getProperty("urban_airship_app_key");
			URBAN_AIRSHIP_APP_SECRET = propConfig.getProperty("urban_airship_app_secret");
			
			AMAZON_S3_SECRET_KEY = propConfig.getProperty("s3.access_secret_key");
			AMAZON_S3_KEY_ID = propConfig.getProperty("s3.access_key_id");
			AMAZON_S3_BUCKET_NAME = propConfig.getProperty("s3.bucket_name");
			
			ACS_APP_KEY = propConfig.getProperty("acs_app_key");
			
			

		} catch (IOException e) {
			LOG.error("Properties File can not be loaded!!! "
					+ e.getLocalizedMessage());
		}

	}

	private String getProps(final String key) {
		String value = "";
		if (propConfig != null) {
			value = propConfig.getProperty(key);
		}
		return value;
	}

	public String getAppcenterUrl() {
		return getProps("appcenter_url");
	}

	public String getAppUrl() {
		return getProps("app_url");
	}

	public static Date convertStringToDate(String requestedDateStr) {

		Date requestedDate;

		try {
			DateFormat formatter = new SimpleDateFormat("MMM DD, YYYY");

			requestedDate = (Date) formatter.parse(requestedDateStr);
		} catch (ParseException e) {
			return null;
		}

		return requestedDate;
	}
	
	/*
	 * Get current date
	 */
	
	public static Date getCurrentDate(){
		DateTime dateTime = new DateTime();
		Date currentDate = dateTime.toDate();
		return currentDate;
	}
	
	/*
	 * Gets next renewal date - adds 1 month to current date
	 */
	public static Date getNextRenewalDate(){
		DateTime nextRenewalDateTime = new DateTime().plusMonths(1);
		return nextRenewalDateTime.toDate();
	}
	
	/*
	 * Convert Date to String
	 */
	public static String convertDateToString(Date date, String format){
		String strDate = null;
		
		if (StringUtils.isEmpty(format)){
				format = "MM DD, yyyy";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		if (date != null){
			strDate = sdf.format(date);
		}
		
		return strDate;
	}
	
	public static String formatTimestamp(Timestamp timestamp) {
		String str = null;
		if (timestamp != null){
			DateTime dt = new DateTime(timestamp);
			DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
			str = dt.toString(fmt);
		}
		return str;	
	}

	public static String formatTimestampWithTime(Timestamp timestamp) {
		String str = null;
		if (timestamp != null){
			DateTime dt = new DateTime(timestamp);
			DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy k:m");
			str = dt.toString(fmt);
		}
		return str;	
	}
	
	public static String formatTimestampOnlyTime(Timestamp timestamp) {
		String str = null;
		if (timestamp != null){
			DateTime dt = new DateTime(timestamp);
			DateTimeFormatter fmt = DateTimeFormat.forPattern("k:m");
			str = dt.toString(fmt);
		}
		return str;	
	}
	
	public static  Context getContext(final String accesstoken,
			final String accesstokensecret, final String realmID, final String dataSource) throws FMSException {

		LOG.info("apptoken inside getContext: "
				+ APP_TOKEN);
		LOG.info("realmID inside getContext: "
				+ realmID);
		LOG.info("OAuth acccess token inside getContext: "
						+ accesstoken);

		ServiceType serviceType;
		if (dataSource.equalsIgnoreCase("QBO")) {
			serviceType = ServiceType.QBO;
		} else {
			serviceType = ServiceType.QBD;
		}

		final OAuthAuthorizer authorizer = new OAuthAuthorizer(
				OAUTH_CONSUMER_KEY, OAUTH_CONSUMER_SECRET, accesstoken,
				accesstokensecret);
		final Context context = new Context(authorizer, APP_TOKEN, serviceType, realmID);

		return context;
	}


	/**
	 * @param tenantId
	 */
	public static boolean ensureTenantFolderExists(Long tenantId) {
		File baseFolder = new File(WebUtils.REPOSITORY_BASE);
		File tenantFolder = new File(WebUtils.REPOSITORY_BASE + "/tenant_"
				+ tenantId);

		if (!baseFolder.exists()) {
			boolean created = baseFolder.mkdir();

			if (!created) {
				LOG.error("Could not create Base folder at : "
						+ baseFolder.getAbsolutePath());
				return false;
			} else {
				LOG.info("Successfully created Base folder at : "
						+ baseFolder.getAbsolutePath());
			}
		}

		if (!tenantFolder.exists()) {
			boolean created = tenantFolder.mkdir();

			if (!created) {
				LOG.error("Could not create Tenant folder at : "
						+ tenantFolder.getAbsolutePath());
				return false;
			} else {
				LOG.info("Successfully created Tenant folder at : "
						+ tenantFolder.getAbsolutePath());
			}
		}

		return true;
	}
}
