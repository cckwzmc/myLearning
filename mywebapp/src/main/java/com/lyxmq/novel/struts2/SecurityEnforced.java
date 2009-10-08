package com.lyxmq.novel.struts2;

/**
 * Implemented by struts2 actions which want to enforce some level of security protection on their action. Available enforcements are ...
 * <ul>
 * <li>- require a logged in user</li>
 * <li>- reguire a valid Web instance to work on</li>
 * <li>- require a specific user role, such as "admin"</li>
 * <li>- require a specific web permission</li>
 *</ul>
 */
public interface SecurityEnforced {

	/**
	 * Does the action require an authenticated user?
	 * <p/>
	 * 
	 * @return boolean True if authenticated user is required, false otherwise.
	 */
	public boolean isUserRequired();

	/**
	 * Does the action require a valid web to work on?
	 * <p/>
	 * This only takes effect if isUserRequired() is 'true'.
	 * <p/>
	 * 
	 * @return boolean True if action web is required, false otherwise.
	 */
	public boolean isRequired();

	/**
	 * What is the required user role, if any?
	 * <p/>
	 * This method only takes effect if isUserRequired() is 'true'.
	 * <p/>
	 * 
	 * @return String The required user role, or null if no role required.
	 */
	public String requiredUserRole();

	/**
	 * What are the required web permissions for this action, if any?
	 * <p/>
	 * This method only takes effect if both isUserRequired() and isRequired()
	 * <p/>
	 * are 'true'.
	 * 
	 * @return short The required web permissions, or -1 if no permissions required.
	 */
	public short requiredPermissions();

}
