package com.uraal.cab.driver.baseClasses;

public interface Constants {
	/*// Request Code
	public static final int requestCode = 101;
	public static final int requestCodeYourChannelDetail = 102;
	public static final int login = 3001;
	public static final int forgetPass = 3002;
	public static final int signup = 3003;
	public static final int logout = 3059;	
	public static final int isValid = 4001;
	
	public final static String normal_login_type = "normal";
	public final static String fb_login_type = "fb";
	public final static String google_login_type = "google";
	
	public final static String personal = "personal";
	public final static String business = "business";	

	public static final String bundleArg = "bundle";
	public static final String title = "title";	
	public static final String password = "password";
	public static final String name = "name";
	public static final String userImageURL = "imgURL";
	public static final String response = "response";
	public static final String list = "list";
	public static final String PopularCategory = "PopularCategory";
	public static final String className = "className";
	public final static String statusOk = "accepted";	
	public final static String statusNotOk = "rejected";
	public final static String success = "Success";
	public final static String dataToFollow = "dataflow";
	public static final String msg = "msg";
	public static final String message = "message";
	public static final String status = "status";	
	public static final String deviceId = "deviceId";
	public static final String map = "map";
	public static final String set = "set";
	public static final String jObject = "jObject";
	public static final String guest = "guest";
	public static final String position = "position";	
	public static final String url = "url";
	public static final String images = "Images";
	public static final String FRAGMENT_TAG = "frag";
	public static final String object = "object";
	public static final String countryCode = "countryCode";
	public static final String flag = "flag";
	public static final String drawerTab = "drawerTab";


	//Shardeprefs
	public static final String VERIFY = "verify";
	public static final String VERIFY_NO = "verify_no";
	public static final String LOGIN = "login";
	public static final String USER_ID = "user_id";
	public static final String friendId = "friendId";
	public static final String emailId = "emailId";
	public static final String socialId = "socialId";
	
	public static final String USER_NAME = "userName";
	//public static final String PH_NUMBER = "phone_no";
	
	public static final String userImage = "userImage";
	public static final String COUNTRY_CODE = "country_code";
	public static final String GENDER = "gender";
	public static final String MALE = "male";
	public static final String FEMALE = "female";
	public static final String loginType = "loginType";
	public static final String DOB = "dob";
	//Sign Up
	public static final String mobileNo = "mobileNo";
	public static final String accountType = "accountType";
	public static final String registrationType = "registrationType";
	
	
	public static final String API_KEY_VALUE = "EFH127FL";
	public static final String API_KEY = "apiKey";
	public static final String NEW_APPS = "newApps";
	public static final int INT = 1;
	public static final int FLOW = 1;
	public static final int NOT_FLOW = 0;


	public static final int STATUS = 1;
	public static final int NOT_STATUS = 0;
	public static final String yourChannels = "yourChannels";
	public static final String followedChannels = "followedChannels";
	public static final String explorer = "explorer";
	//public static final String type = "loginType";
	public static final String catId = "catId";
	public static final String catName = "catName";
	public static final String channelId = "channelId";
	public static final String postId = "postId";
	public static final String parentId = "parentId";
	public static final String channel = "channel";
	public static final String channelName = "channelName";
	public static final String channelDescription = "channelDescription";
	public static final String channelType = "channelType";
	public static final String channelMode = "channelMode";
	public static final String channelImage = "channelImage";
	public static final String channelStatus = "channelStatus";
	public static final String Post = "Post";
	public static final String description = "description";
	public static final String image = "image";
	public static final String Comment = "Comment";
	public static final String comment = "comment";
	public static final String username = "username";
	public static final String main_comment = "main_comment";
	public static final String childComments = "childComments";
	public static final String packageName = "packageName";
	public static final String appStatus = "appStatus";
	public static final String feedback = "feedback";
	public static final String interest = "interest";
	public static final String permission = "permission";
	public static final String gcmId = "gcmId";
	public static final String notificationStatus = "notificationStatus";

	public static final String showSubscribeFeeds = "showSubscribeFeeds";
	public static final String allowPost = "allowPost";
	public static final String allowSearch = "allowSearch";
	public static final String allowFeeds = "allowFeeds";
	public static final String allowCommentOnPost = "allowCommentOnPost";


	// parameters
	public final static String p_device_brand = "deviceBrand";
	public final static String deviceType = "deviceType";
	public final static String p_device_os = "deviceOs";

	public final static String p_first_name = "fn";
	public final static String p_last_name = "ln";

	public final static String p_fb_id = "fb_id";

	
	public final static String data = "data";
	public final static String p_user = "User";
	public final static String p_categories = "categories";
	public final static String p_message = "message";
	public final static String p_app_list = "appList";
	public final static String p_appName = "appName";
	public final static String p_appPack = "appPack";
	public final static String p_app_url = "app_url";

	public final static String p_friendName = "friendName";
	public final static String p_friendId = "friendId";
	//public final static String p_channel_id = "channel_Id";
	public final static String p_contact_list = "contactList";

	public final static String p_channel_type = "channel_type";
	public final static String p_channel_mode = "channel_mode";
	public final static String p_fileToUpload = "fileToUpload";

	public final static String p_show_subscribe_feeds = "show_subscribe_feeds";
	public final static String p_allow_post = "allow_post";
	public final static String p_allow_search = "allow_search";
	public final static String p_allow_feeds = "allow_feeds";
	//public final static String android = "Android";	
	public final static String p_receiver_id = "receiver_id";
	public final static String senderId = "senderId";
	public final static String requestType = "requestType";
	public final static String shareTypeId = "shareTypeId";



	public final static String id = "id";
	public final static String fileSize = "fileSize";
	public final static String numDownloads = "numDownloads";
	public final static String datePublished = "datePublished";
	public final static String versionName = "versionName";
	public final static String operatingSystems = "operatingSystems";
	public final static String screenshots = "screenshots";
	public final static String rating = "rating";
	public final static String price = "price";
	public final static String category = "category";
	public final static String categoryName = "categoryName";
	public final static String priceCurrency = "priceCurrency";
	public final static String icon = "icon";
	public final static String count = "count";
	public final static String display = "display";
	public final static String supportEmail = "supportEmail";
	public final static String supportUrl = "supportUrl";
	public final static String inAppBilling = "inAppBilling";
	public final static String adsSupported = "adsSupported";
	public final static String value = "value";
	public final static String five = "five";
	public final static String four = "four";
	public final static String three = "three";
	public final static String two = "two";
	public final static String one = "one";
	public final static String storeCategory = "storeCategory";
	public final static String topDeveloper = "topDeveloper";
	public final static String editorsChoice = "editorsChoice";
	public final static String changelog = "changelog";
	public final static String contentRating = "contentRating";
	public final static String video = "video";
	public final static String unknown = "unknown";
	public final static String created = "created";
	public final static String modified = "modified";
	public static final String companyName = "companyName";
	public static final String companyUrl = "companyUrl";


	public final static String body = "body";
	public final static String author = "author";
	public final static String date = "date";

	public final static String postTitle = "postTitle";
	public final static String postImage = "postImage";
	public final static String postDescription = "postDescription";
	public final static String postCreatedTime = "postCreatedTime";
	//public final static String userName = "userName";
	public final static String commentTime = "commentTime";
	public final static String feeds = "feeds";

	public final static String package_ = "package";
	public final static String feedType = "feedType";
	//public final static String YourCommunityApps = "YourCommunityApps";
	//public final static String GAPPSSCommunityApps = "GAPPSSCommunityApps";
	public final static String GAPPSSCommunityApps = "CommunityApps";
	public final static String freeApps = "freeApps";
	public final static String paidApps = "paidApps";
	public final static String topGrossingApps = "topGrossingApps";
	public final static String appId = "appId";
	public final static String appName = "appName";
	public final static String appPackageName = "appPackageName";
	public final static String appUrl = "appUrl";
	public final static String rank = "rank";

	public final static String brand = "brand";
	public final static String os = "os";
	public final static String userEmail = "userEmail";
	public final static String totalApps = "totalApps";
	public final static String totalCommanApps = "totalCommanApps";
	public final static String totalDiscoverApps = "totalDiscoverApps";
	public final static String user = "user";
	public final static String friendstr = "friend";
	public final static String appList = "appList";
	public static final String topRatedPost = "topRatedPost";
	public static final String followersCount = "followersCount";
	public static final String postCount = "postCount";
	public static final String postName = "postName";
	public static final String favStatus = "favStatus";
	public static final String loginStatus = "loginStatus";

	//give type = 1 post,2 shareapp,3 request in getAllInbox
	public final static int feedTypeLeaveChannel = 0;
	public final static int feedTypeJoinChannel = 1;
	public final static int feedTypePost = 2;
	public final static int feedTypeCreateChannel = 3;
	public final static int feedTypeFriendRequest = 4;
	public final static int feedTypeComment = 5;
	public final static int inboxTypePost = 1;
	public final static int inboxTypeRequest = 2;
	public final static int inboxTypeShareAPPS = 3;
	public final static int inboxTypeFriendRequest = 4;

	public final static int popularCategories = 1;
	public final static int popularApp = 2;
	public final static int popularLink = 3;

	public static final String friendType = "isFriend";
	public static final String noFriendStr = "Uraal users";
	public static final String friendStr = "Friends";
	public static final String sentRequestStr = "Request sent";
	public static final String receivedReqFriendStr = "Received request";
	public final static int sentRequest = 0;
	public final static int receivedRequest = 1;
	public final static int friend = 2;
	public final static int noFriend = 3;
	public static final CharSequence allow = "Allow";
	public static final CharSequence waitingResponse = "Waiting response";
	public static final int accept = 1;
	public static final int deny = 2;
	public static final String contact = "contact";
	public static final String community = "Apps";
	public static final String inbox = "Inbox";
	public static final String contactT = "Contact";
	public static final String AppStatusFragment = "Your Apps";
	public static final String ChannelFragment = "Channels";
	public static final String aboutUs = "About Us";
	public static final String account = "Account";
	public static final String appCat = "App Categories";
	public static final String appDetail = "App Detail";
	public static final String compareProfile = "User Comparison";
	public static final String friendInterestT = "Interests";
	public static final String frndActivity = "Activities";
	public static final String apps = "Apps";
	public static final String frndChannels = "Channels";
	public static final String shareApps = "Shared Apps";
	public static final String InboxSharedAppFragment = "Shared Apps Inbox";
	public static final String InboxRequestFragment = "Request Inbox";
	public static final String InboxPostFragment = "Post Inbox";
	public static final String Notification = "Notification";
	public static final String TearmAndConditionFragment = "Tearm And Conditions";
	public static final String MyAppShareActivity = "MyAppShareActivity";
	public static final String AllFeedsFragment = "All Feeds";
	public static final String FriendFeedsFragment = "Friend's Feeds";
	public static final String ChannelsFeedsFragment = "Channel Feeds ";
	public static final String FavoritesFeedsFragment = "Favorite Feeds ";
	public static final String PopularChannelListFragment = "Channels";
	public static final String NotificationListFragment = "Notification List";
	public static final String settingStaus = "settingStaus";
	public static final String ownIsGrant = "ownIsGrant";
	public static final String friendIsGrant = "friendIsGrant";
	public static final String appRequest = "appRequest";
	public static final String isGrant = "isGrant";
	public static final String time = "time";
	public static final String requests = "Requests";


	public static final String hot = "hot";
	public static final String rate = "rate";
	public static final String newly = "new";
	public static final String appPrice = "appPrice";

	public static final String TABLE_APP_DETAIL = "app_detail";
	public static final String TABLE_NOTIFICATION = "notification";
	public static final String TABLE_UNREAD_COUNT = "unread_count";	
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_SUPPORT_EMAIL = "supportEmail";
	public static final String COLUMN_SUPPORT_URL = "supportUrl";
	public static final String COLUMN_ICON = "icon";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_STORE_CATEGORY = "storeCategory";
	public static final String COLUMN_PRICE = "price";
	public static final String COLUMN_PRICE_CURRENCY = "priceCurrency";
	public static final String COLUMN_INAPP_BILLING = "inAppBilling";
	public static final String COLUMN_ADS_SUPPORTED = "adsSupported";
	public static final String COLUMN_RATING_COUNT = "count";
	public static final String COLUMN_RATING_DISPLAY = "display";
	public static final String COLUMN_RATING_VALUE = "value";
	public static final String COLUMN_RATING_FIVE = "five";
	public static final String COLUMN_RATING_FOUR = "four";
	public static final String COLUMN_RATING_THREE = "three";
	public static final String COLUMN_RATING_TWO = "two";
	public static final String COLUMN_RATING_ONE = "one";
	public static final String COLUMN_TOP_DEVELOPER = "topDeveloper";
	public static final String COLUMN_EDITORS_CHOICE = "editorsChoice";
	public static final String COLUMN_SCREENSHOTS = "screenshots";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_CHANGELOG = "changelog";
	public static final String COLUMN_DATE_PUBLISHED = "datePublished";
	public static final String COLUMN_FILE_SIZE = "fileSize";
	public static final String COLUMN_NUM_DOWNLOADS = "numDownloads";
	public static final String COLUMN_VERSION_NAME = "versionName";
	public static final String COLUMN_VIDEO = "video";
	public static final String COLUMN_OPERATING_SYSTEMS = "operatingSystems";
	public static final String COLUMN_CONTENT_RATING	 = "contentRating";
	public static final String COLUMN_PACKAGE	 = "packageId";
	public static final String COLUMN_UNKNOWN	 = "unknown";
	public static final String COLUMN_TYPE	 = "type";
	public static final String COLUMN_MESSAGE	 = "message";
	public static final String COLUMN_USER_ID	 = "userId";
	public static final String COLUMN_TIME	 = "time";


	public static final String COLUMN_CREATED	 = "created";
	public static final String COLUMN_MODIFIED	 = "modified";
	public static final String COLUMN_STATUS	 = "status";
	public static final String COLUMN_RANK	 = "rank";
	public static final String COLUMN_APP_STATUS	 = "appStatus";

	public static final int KNOWN	 = 1;
	public static final int UNKNOWN	 = 0;
	public static final String playStoreURL = "https://play.google.com/store/apps/details?id=";
	public static final String isInterestedApps = "isIntApps";

	public static final String CONTACTS = "CONTACTS";
	public static final String CHANNELS = "CHANNELS";


	public static final int APPS = 1;
	public static final int CHANNEL = 2;

	public static final int requestAccess = 0;
	public static final int accessAllOW = 1;
	public static final int  awaitingApproval= 2;
	public static final String tagedId = "tagedId";

	public static final String ExplorerChannelsFragment = "Explorer Channels";
	public static final String YourChannelsFragment = "Your Channels";
	public static final String FollowedChannelsFragment = "Following Channels";
	public static final String ChannelSettingFragment = "Channel Setting";
	public static final String CommentScreenFragment = "Comment";
	public static final String CountryFragment = "Countries";
	public static final String CreateChannelFragment = "Create Channel";
	public static final String CreatePostFragment = "Create Post";
	public static final String ExplorerChannelDetailFragment = "Channel Details";
	public static final String FollowingChannelDetailFragment = "Channel Details";
	public static final String FragmentChannelSetting = "Channel Settings";
	public static final String InboxAllFragment = "All Inbox";
	public static final String InviteFriendFragment = "Invite Friend";
	public static final String PostListFragment = "Post List";
	public static final String SettingFragment = "Setting";
	public static final String StatsFragment = "Stats";
	public static final String YourChannelDetailFragment = "Channel Details";
	public static final String feedCount = "feedCount";
	public static final String inboxCount = "inboxCount";
	public static final String contactCount = contact;
	public static final String contactUnreadCount = "contactUnread";
	public static final String lastCommentId = "lastCommentId";	
	public static final String HomeActivityDrawer = "HomeActivityDrawer";
	public static final String ChannelAppsShareFragment = "ChannelAppsShareFragment";

	public static final String isShareApps = "isShareApps";
	public static final String setSuccess = "Set visibility successfully";
	public static final String selectContact = "Please select contact!";

	public static final String COLUMN_POST_ID = postId;
	public static final String COLUMN_COMMENT_COUNT = "commentCount";
	public static final String isValidUser = "isValidUser";
	public static final String isEditProfile = "isEditProfile";
	public static final String isSocial = "isSocial;";
	
	
	public static final String TABLE_START_ROUTE = "start_route";
	public static final String TABLE_END_ROUTE = "end_route";
	public static final String TABLE_FAVORITE_LIST = "favorite_list";
	public static final String TABLE_NOTE = "note";


	TABLE FIELD NAME

	public static final String COLUMN_ROUTE_NAME= "route_name";
	public static final String COLUMN_LAT= "lat";
	public static final String COLUMN_LOG= "log";
	public static final String COLUMN_CREATED_DATE= "created_date";
	public static final String COLUMN_IS_COMPLETED= "is_completed";
	public static final String COLUMN_UPDATE_DATE= "updated_date";
	public static final String COLUMN_START_ROUTE_ID= "start_route_id";
	public static final String COLUMN_NOTE_ID= "note_id";
	public static final String COLUMN_IS_FAV= "is_fav";
	public static final String COLUMN_NOTE_NAME= "note_name";
	public static final String COLUMN_IMAGE_URL= "image_url";
	public static final String COLUMN_TITLE= "title";
	public static final String COLUMN_NOTES_OF= "notes_of";
	public static final String COLUMN_NOTES_OF_ID= "notes_of_id";
	public static final String COLUMN_CAPTION= "caption";
	public static final String COLUMN_IMG_COUNT= "img_count";
	public static final String COLUMN_ROW_ID= "row_id";
	public static final String COLUMN_NOTE_TEXT= "note_text";
	public static final String COLUMN_IS_IMG= "is_img";
	public static final String COLUMN_ROW_ID_OF_START_ROUTE= "row_id_of_statr_route";

	public static final String FAVORITES= "Favorites";
	public static final String LISTING= "Listing";


	public static final int QUARY_RESPONSE_INT = -1;
	public static final long QUARY_RESPONSE_LONG = -1;	
	public static final int LOCATION = 101;
	public static final int LOCATION_2 = 102;
	public static final int SEARCH_ADDRESS = 103;
	public static final int ROUTE_TYPE = 022;
	public static final int FAV_TYPE = 023;
	public static final int LISTING_TYPE = 024;

	public static final int FAV = 1;
	public static final int LIST = 0;
	public static final String androidOS = "android";*/

		// Request Code
		public static final int requestCode = 101;
		public static final int requestCodeYourChannelDetail = 102;
		public static final int login = 3001;
		public static final int forgetPass = 3002;
		public static final int signup = 3003;
		public static final int getHistory = 3004;
		public static final int getPayroll = 3005;
		public static final int logout = 3059;	
		public static final int isValid = 4001;
		
		
		public static final int FLOW = 1;
		public static final int NOT_FLOW = 0;
		public final static String normal_login_type = "normal";
		public final static String fb_login_type = "fb";
		public final static String google_login_type = "google";
		
		public final static String personal = "personal";
		public final static String business = "business";	

		public static final String bundleArg = "bundle";
		public static final String title = "title";	
		public static final String password = "password";
		public static final String name = "name";
		public static final String userImageURL = "imgURL";
		public static final String response = "response";
		public static final String list = "list";
		public static final String PopularCategory = "PopularCategory";
		public static final String className = "className";
		public final static String statusOk = "accepted";	
		public final static String statusNotOk = "rejected";
		public final static String success = "Success";
		public final static String dataToFollow = "dataflow";
		public static final String msg = "msg";

		public static final String status = "status";	
		public static final String deviceId = "deviceId";
		public static final String map = "map";
		public static final String set = "set";
		public static final String jObject = "jObject";
		public static final String guest = "guest";
		public static final String position = "position";	
		public static final String url = "url";
		public static final String images = "Images";
		public static final String FRAGMENT_TAG = "frag";
		public static final String object = "object";
		public static final String flag = "flag";
		public static final String drawerTab = "drawerTab";


		//Shardeprefs
		public static final String VERIFY = "verify";
		public static final String VERIFY_NO = "verify_no";
		public static final String LOGIN = "login";
		public static final String userId = "userId";
		public static final String friendId = "friendId";
		public static final String emailId = "emailId";
		public static final String socialId = "socialId";
		public static final String userName = "userName";
		
		public static final String userImage = "userImage";
		public static final String countryCode = "countryCode";
		public static final String gender = "gender";
		public static final String male = "male";
		public static final String female = "female";
		public static final String loginType = "loginType";
		public static final String dob = "dob";
		//Sign Up
		public static final String mobileNo = "mobileNo";
		public static final String accountType = "accountType";
		public static final String registrationType = "registrationType";
		
		public static final String API_KEY_VALUE = "EFH127FL";
		public static final String API_KEY = "apiKey";
		
		public static final String description = "description";
		public static final String image = "image";
		public static final String comment = "comment";
		public static final String packageName = "packageName";
		public static final String feedback = "feedback";
		public static final String interest = "interest";
		public static final String permission = "permission";
		public static final String gcmId = "gcmId";
		public static final String notificationStatus = "notificationStatus";
		public final static String deviceType = "deviceType";
		public final static String firstName = "fn";
		public final static String lastname = "ln";
		public final static String fbId = "fb_id";
		public final static String id = "id";
		public final static String user = "user";
		public final static String message = "message";
		public static final String isEditProfile = "isEditProfile";
		public static final String isValidUser = "isValidUser";
		public static final String isSocial = "isSocial;";
		public final static String data = "data";
		public static final String p_user = "User";
		public static final String rydeId = "rydeId";
		public static final String billAmout = "billAmout";
		public static final String date = "date";
		public static final String time = "time";
		public static final String carType = "carType";
		public static final String carName = "carName";

		
		
		/*Fragment TAG value*/
		public static final String BookARyde = "Book A Ryde";
		public static final String ShareARyde = "Share A Ryde";
		public static final String Payment = "Payment";
		public static final String ApplyPromoCode = "Apply Promo Code";
		public static final String RydeHistory = "Ryde History";
		public static final String Profile = "Profile";
		public static final String Help = "Help";
		public static final String CustomerSupport = "Customer Support";
		
		
		/*DB TABLE*/
		public static final String TABLE_NOTIFICATION = "notification";
		public static final String COLUMN_ICON = "icon";
		public static final String COLUMN_TYPE	 = "type";
		public static final String COLUMN_MESSAGE	 = "message";
		public static final String COLUMN_USER_ID	 = "userId";
		public static final String COLUMN_TIME	 = "time";

		public static final String TABLE_START_ROUTE = "start_route";
		public static final String TABLE_END_ROUTE = "end_route";
		public static final String TABLE_FAVORITE_LIST = "favorite_list";
		public static final String TABLE_NOTE = "note";


		/*TABLE FIELD NAME*/

		public static final String COLUMN_ROUTE_NAME= "route_name";
		public static final String COLUMN_LAT= "lat";
		public static final String COLUMN_LOG= "log";
		public static final String COLUMN_CREATED_DATE= "created_date";
		public static final String COLUMN_IS_COMPLETED= "is_completed";
		public static final String COLUMN_UPDATE_DATE= "updated_date";
		public static final String COLUMN_START_ROUTE_ID= "start_route_id";
		public static final String COLUMN_NOTE_ID= "note_id";
		public static final String COLUMN_IS_FAV= "is_fav";
		public static final String COLUMN_NOTE_NAME= "note_name";
		public static final String COLUMN_IMAGE_URL= "image_url";
		public static final String COLUMN_TITLE= "title";
		public static final String COLUMN_NOTES_OF= "notes_of";
		public static final String COLUMN_NOTES_OF_ID= "notes_of_id";
		public static final String COLUMN_CAPTION= "caption";
		public static final String COLUMN_IMG_COUNT= "img_count";
		public static final String COLUMN_ROW_ID= "row_id";
		public static final String COLUMN_NOTE_TEXT= "note_text";
		public static final String COLUMN_IS_IMG= "is_img";
		public static final String COLUMN_ROW_ID_OF_START_ROUTE= "row_id_of_statr_route";

		public static final String FAVORITES= "Favorites";
		public static final String LISTING= "Listing";


		public static final int QUARY_RESPONSE_INT = -1;
		public static final long QUARY_RESPONSE_LONG = -1;	
		public static final int LOCATION = 101;
		public static final int LOCATION_2 = 102;
		public static final int SEARCH_ADDRESS = 103;
		public static final int ROUTE_TYPE = 022;
		public static final int FAV_TYPE = 023;
		public static final int LISTING_TYPE = 024;

		public static final int FAV = 1;
		public static final int LIST = 0;
		public static final String androidOS = "android";
	
		
		

	}


