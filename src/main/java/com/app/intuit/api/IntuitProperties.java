/**
 * 
 */
package com.app.intuit.api;

/**
 * @author shiva
 *
 */
public interface IntuitProperties {

	/**
	 * Returns Intuit Consumer Key obtained during Application registration.
	 * 
	 * See {@link https://developer.intuit.com} on how to obtain Consumer Key for the app.
	 * 
	 * @return Consumer Key
	 */
	String getConsumerKey();

	/**
	 * Returns Intuit Consumer Secret obtained during Application registration.
	 * 
	 * See {@link https://developer.intuit.com} on how to obtain Consumer Secret for the app.
	 * 
	 * @return Consumer Secret
	 */
	String getConsumerSecret();

	/**
	 * Returns Intuit Access Token for the Quickbooks user's Account Information.
	 * 
	 * An access token is obtained during OAuth handshake with the quickbooks.
	 * 
	 * See {@link https://developer.intuit.com/docs/0050_quickbooks_api/0020_authentication_and_authorization/connect_from_within_your_app} for more information.
	 * 
	 * @return Clients Access Token
	 */
	String getAccessToken();

	/**
	 * Returns Intuit Access Secret for the Quickbooks user's Account Information.
	 * 
	 * An access token is obtained during OAuth handshake with the quickbooks.
	 * 
	 * See {@link https://developer.intuit.com/docs/0050_quickbooks_api/0020_authentication_and_authorization/connect_from_within_your_app} for more information.
	 * 
	 * @return Clients Access Secret
	 */
	String getAccessTokenSecret();

	/**
	 * Returns Company Realm ID of the company which is registered with quickbooks.
	 * 
	 * @return
	 */
	String getCompanyId();

	String getBaseUrl();

}
