package migrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryList {
	public static final Map<String,String> queryMap= new HashMap<String,String>();
	static
	{
		queryMap.put("pps_accounts", 
				"select * " +
				"from pps_accounts " +
				"where account_id = ?");
		
		queryMap.put("pps_access_keys",
				"select * " +
				"from pps_access_keys " +
				"where acl_id = ?");
		
		queryMap.put("pps_account_features",
				"select * " +
				"from pps_account_features " +
				"where account_id = ?");
		
		queryMap.put("pps_account_history",
				"select * " +
				"from pps_account_history " +
				"where account_id = ?");
		
		queryMap.put("pps_account_permissions",
				"select * " +
				"from pps_account_permissions " +
				"where account_id = ?");
		
		queryMap.put("pps_acl_entries",
				"select * " +
				"from pps_acl_entries " +
				"where acl_id in (select acl_id " +
                                 "from pps_acl_rollup " +
                                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_acl_fields",
				"select * " +
				"from pps_acl_fields " +
				"where acl_id in (select acl_id " +
                                 "from pps_acl_rollup " +
                                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_acl_multi_fields",
				"select * " +
				"from pps_acl_multi_fields " +
				"where acl_id in (select acl_id " +
                                 "from pps_acl_rollup " +
                                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_acl_preferences",
				"select * " +
				"from pps_acl_preferences " +
				"where acl_id in (select acl_id " + 
                                 "from pps_acl_rollup " + 
                                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_acl_quotas",
				"select * " +
				"from pps_acl_quotas " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_acl_rollup",
				"select * " +
				"from pps_acl_rollup " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_acls",
				"select * " +
				"from pps_acls " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?) " +
				"order by acl_id");
		
		queryMap.put("pps_action_principal_status",
				"select * " +
				"from pps_action_principal_status " +
				"where action_id in (select action_id " +
				                    "from pps_actions " +
				                    "where target_acl_id in (select acl_id " +
				                                            "from pps_acl_rollup " +
				                                            "where parent_acl_id = ?))");
		queryMap.put("pps_actions",
				"select * " +
				"from pps_actions " +
				"where target_acl_id in (select acl_id  " +
				                        "from pps_acl_rollup " +
				                        "where parent_acl_id = ?)");
		
		queryMap.put("pps_asset_annotations",
				"select * " +
				"from pps_asset_annotations " +
				"where asset_id in(select asset_id " +
				                  "from pps_sco_assets " +
				                  "where sco_id in (select sco_id  " +
				                                   "from pps_scos " +
				                                   "where sco_id in (select acl_id " +
				                                                    "from pps_acl_rollup " +
				                                                    "where parent_acl_id = ?)))");

		queryMap.put("pps_asset_entries",
				"select * " +
				"from pps_asset_entries " +
				"where asset_id in(select asset_id " +
				                  "from pps_sco_assets  " +
				                  "where sco_id in (select sco_id " +
				                                   "from pps_scos " +
				                                   "where sco_id in (select acl_id " +
                                                                    "from pps_acl_rollup " +
                                                                    "where parent_acl_id = ?)))");

		
		/*queryMap.put("pps_asset_hosts",
				"select * " +
				"from pps_asset_hosts " +
				"where asset_id in(select asset_id " +
				                  "from pps_sco_assets " +
				                  "where sco_id in (select sco_id " +
				                                   "from pps_scos where account_id = ?))");*/
		queryMap.put("pps_asset_interactions",
				"select * " +
				"from pps_asset_interactions " +
				"where asset_id in(select asset_id " +
				                  "from pps_sco_assets " +
				                  "where sco_id in (select sco_id " +
				                                   "from pps_scos  " +
				                                   "where sco_id in (select acl_id " +
                                                                    "from pps_acl_rollup " +
                                                                    "where parent_acl_id = ?)))");



		queryMap.put("pps_asset_responses",
				"select * " +
				"from pps_asset_responses " +
				"where interaction_id in (select interaction_id " +
				                         "from pps_asset_interactions " +
				                         "where asset_id in(select asset_id " +
				                                           "from pps_sco_assets " +
				                                           "where sco_id in (select sco_id  " +
				                                                            "from pps_scos " +
 				         				                                    "where sco_id in (select acl_id " +
   						                                                                     "from pps_acl_rollup " +
						                                                                     "where parent_acl_id = ?))))");


		queryMap.put("pps_assets",
				"select * " +
				"from pps_assets " +
				"where asset_id in(select asset_id " +
				                  "from pps_sco_assets " +
				                  "where sco_id in (select sco_id " +
				                                   "from pps_scos " +
				                                   "where sco_id in (select acl_id " +
                                                                    "from pps_acl_rollup " +
                                                                    "where parent_acl_id = ?)))");


		queryMap.put("pps_fields",
				"select * " +
				"from pps_fields " +
				"where account_id = ?");

		queryMap.put("pps_group_member_rollup",
				"select * " +
				"from pps_group_member_rollup " +
				"where principal_id in (select principal_id " +
				                       "from pps_principals " +
				                       "where account_id = ?)");

		queryMap.put("pps_group_members",
				"select * " +
				"from pps_group_members " +
				"where principal_id in (select principal_id " +
				                       "from pps_principals " +
				                       "where account_id = ?)");
		/*TODO: Do we need this?*/
		/*queryMap.put("pps_hosted_principal_history",
				"select * " +
				"from pps_hosted_principal_history " +
				"where principal_id in (select principal_id  " +
				                       "from pps_principals " +
				                       "where account_id = ?)");*/
		/*Should we use target_sco_id or current_sco_id*/
		queryMap.put("pps_learning_path",
				"select * " +
				"from pps_learning_path " +
				"where current_sco_id in (select sco_id " +
				                         "from pps_scos " +
  	                                     "where sco_id in (select acl_id " +
                                                          "from pps_acl_rollup " +
                                                          "where parent_acl_id = ?))");


		/*queryMap.put("pps_meeting_start_locks",
				"select * " +
				"from pps_meeting_start_locks " +
				"where sco_id in (select sco_id " +
				                 "from pps_scos " +
				                 "where account_id = ?)");*/

		queryMap.put("pps_permission_activities",
				"select * " +
				"from pps_permission_activities " +
				"where sco_id in (select sco_id " +
				                 "from pps_scos " +
                                 "where sco_id in (select acl_id " +
                                                  "from pps_acl_rollup " +
                                                  "where parent_acl_id = ?))");


		queryMap.put("pps_principals",
				"select * " +
				"from pps_principals " +
				"where account_id = ?");

		/*What about hostID??*/
		/*queryMap.put("pps_sco_active_hosts",
				"select * " +
				"from pps_sco_active_hosts " +
				"where sco_id in (select sco_id " +
				                 "from pps_scos " +
				                 "where account_id = ?)");*/

		queryMap.put("pps_sco_assets",
				"select * " +
				"from pps_sco_assets " +
				"where sco_id in(select sco_id " +
				                "from pps_scos " +
                                "where sco_id in (select acl_id " +
                                                 "from pps_acl_rollup " +
                                                 "where parent_acl_id = ?))");


		/*queryMap.put("pps_sco_locks",
				"select * " +
				"from pps_sco_locks " +
				"where sco_id in(select sco_id " +
				                "from pps_scos " +
				                "where account_id = ?)");*/


		queryMap.put("pps_sco_questions",
				"select * " +
				"from pps_sco_questions " +
				"where sco_id in(select sco_id " +
				                "from pps_scos " +
                                "where sco_id in (select acl_id " +
                                                 "from pps_acl_rollup " +
                                                 "where parent_acl_id = ?))");


		queryMap.put("pps_sco_receipts",
				"select * " +
				"from pps_sco_receipts " +
				"where sco_id in(select sco_id " +
				                "from pps_scos " +
                                "where sco_id in (select acl_id " +
                                                 "from pps_acl_rollup " +
                                                 "where parent_acl_id = ?))");


		queryMap.put("pps_sco_shortcuts",
				"select * " +
				"from pps_sco_shortcuts " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?)");

		/*Ordering because of self-referential constraint*/
		queryMap.put("pps_scos",
				"select * " +
				"from pps_scos " +
                "where sco_id in (select acl_id " +
                                 "from pps_acl_rollup " +
                                 "where parent_acl_id = ?) " +
				"order by sco_id");

		queryMap.put("pps_threshold_exceeded",
				"select * " +
				"from pps_threshold_exceeded " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?)");

		queryMap.put("pps_transcript_counts",
				"select * " +
				"from pps_transcript_counts " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?)");

		queryMap.put("pps_transcript_details",
				"select * " +
				"from pps_transcript_details " +
				"where transcript_id in (select transcript_id " +
				                        "from pps_transcripts " +
				                        "where sco_id in(select sco_id " +
				                                        "from pps_scos " +
    				                                    "where sco_id in (select acl_id " +
		                                                                 "from pps_acl_rollup " +
		                                                                 "where parent_acl_id = ?)))");


		queryMap.put("pps_transcripts",
				"select * " +
				"from pps_transcripts " +
				"where sco_id in(select sco_id  " +
				                "from pps_scos " +
                                "where sco_id in (select acl_id " +
                                                 "from pps_acl_rollup " +
                                                 "where parent_acl_id = ?))");


		queryMap.put("pps_trees",
				"select * " +
				"from pps_trees " +
				"where account_id = ?");

		/*What iF user_id is NULL??*/
		queryMap.put("pps_user_sessions",
				"select * " +
				"from pps_user_sessions " +
				"where user_id in (select principal_id " +
				                  "from pps_principals " +
				                  "where account_id = ?)");

		queryMap.put("pps_users",
				"select * " +
				"from pps_users " +
				"where user_id in (select principal_id " +
				                  "from pps_principals " +
				                  "where account_id = ?)");
		

		queryMap.put("pps_acl_quotas_history",
				"select * " +
				"from pps_acl_quotas_history " +
				"where acl_id in (select acl_id " +
				                 "from pps_acl_rollup " +
				                 "where parent_acl_id = ?)");
		
		queryMap.put("pps_report_descriptors",
				"select * " +
				"from pps_report_descriptors " +
				"where owner_id in (select principal_id " +
				   				   "from pps_principals " +
				   				   "where account_id = ?)");

		queryMap.put("pps_field_names", 
				"select pf.field_id as FIELD_ID,pfn.xml_name as XML_NAME " +
                "from pps_fields pf, pps_field_names pfn " +
                "where pf.field_id = pfn.field_id " +
                "and pf.account_id = ? " +
                "UNION " +
                "select pamf.field_id as FIELD_ID,pfn.xml_name as XML_NAME " +
                "from pps_acl_multi_fields pamf, pps_field_names pfn " +
                "where pamf.field_id = pfn.field_id " +
                "and pamf.acl_id in (select acl_id " +
				            	    "from pps_acl_rollup par " +
					                "where par.parent_acl_id = ?) " +
                "UNION " +
                "select paf.field_id as FIELD_ID,pfn.xml_name as XML_NAME " +
                "from pps_acl_fields paf, pps_field_names pfn " +
                "where paf.field_id = pfn.field_id " +
                "and paf.acl_id in (select acl_id " +
	    		                   "from pps_acl_rollup par " +
				                   "where par.parent_acl_id = ?)");
		
	}
	
	public static final List<String> tableList = new ArrayList<String>();
	static
	{	
		/*TODO: Do we need to add 
		 * pps_cost_center_audit,
		 * pps_asset_hosts,
		 * pps_hosted_principal_history
		 * pps_meeting_start_locks
		 * pps_sco_active_hosts
		 * pps_sco_locks/
		
		/*Order of tables is important here to prevent foreign keys from breaking on insertion*/
		tableList.add("pps_acls");
		tableList.add("pps_acl_rollup");	
		tableList.add("pps_accounts");
		tableList.add("pps_principals");
		tableList.add("pps_access_keys");
		tableList.add("pps_account_features");
		tableList.add("pps_account_history");
		tableList.add("pps_account_permissions");
		tableList.add("pps_acl_entries");
		tableList.add("pps_field_names");
		tableList.add("pps_acl_fields");	
		tableList.add("pps_acl_multi_fields");
		tableList.add("pps_acl_preferences");
		tableList.add("pps_acl_quotas");
		tableList.add("pps_acl_quotas_history");			
		tableList.add("pps_actions");
		tableList.add("pps_action_principal_status");
		tableList.add("pps_assets");
		tableList.add("pps_asset_annotations");
		tableList.add("pps_asset_entries");
		//tableList.add("pps_asset_hosts");
		tableList.add("pps_asset_interactions");
		tableList.add("pps_asset_responses");		
		tableList.add("pps_fields");
		tableList.add("pps_group_member_rollup");
		tableList.add("pps_group_members");
		//tableList.add("pps_hosted_principal_history");		
		tableList.add("pps_scos");
		//tableList.add("pps_sco_active_hosts");
		tableList.add("pps_sco_assets");
		//tableList.add("pps_sco_locks");
		tableList.add("pps_sco_questions");
		tableList.add("pps_users");
		tableList.add("pps_sco_receipts");
		tableList.add("pps_sco_shortcuts");				
		tableList.add("pps_learning_path");
		//tableList.add("pps_meeting_start_locks");
		tableList.add("pps_permission_activities");
		tableList.add("pps_threshold_exceeded");
		tableList.add("pps_user_sessions");
		tableList.add("pps_transcripts");
		tableList.add("pps_transcript_counts");
		tableList.add("pps_transcript_details");		
		tableList.add("pps_trees");
		tableList.add("pps_report_descriptors");
		
	}
	
	public static final List<String> idGroups = new ArrayList<String>();
	static
	{		
		/*TODO: Do we need to add PPS_SCO_QUESTIONS.question_id here?*/
		/*TODO: Do we need to add pps_threshold_exceeded.THRESHOLD_RECORD_ID here?*/
		idGroups.add("//table[@name='pps_acls']/row/data[@name='ACL_ID']");
		idGroups.add("//table[@name='pps_account_history']/row/data[@name='HISTORY_ID']");
		idGroups.add("//table[@name='pps_assets']/row/data[@name='ASSET_ID']");
		idGroups.add("//table[@name='pps_permission_activities']/row/data[@name='ACTIVITY_ID']");
		idGroups.add("//table[@name='pps_user_sessions']/row/data[@name='SESSION_ID']");
		idGroups.add("//table[@name='pps_transcripts']/row/data[@name='TRANSCRIPT_ID']");
		idGroups.add("//table[@name='pps_field_names']/row/data[@name='FIELD_ID']");
		idGroups.add("//table[@name='pps_acl_multi_fields']/row/data[@name='DF_ID']");
		idGroups.add("//table[@name='pps_acl_quotas_history']/row/data[@name='HISTORY_ID']");
		idGroups.add("//table[@name='pps_asset_interactions']/row/data[@name='INTERACTION_ID']");
		idGroups.add("//table[@name='pps_report_descriptors']/row/data[@name='REPORT_ID']");
				
	}
	
	public static final List<String> allIdCollections = new ArrayList<String>();
	static
	{		
		allIdCollections.add("//table[@name='pps_access_keys']/row/data[@name='ACL_ID']");
		allIdCollections.add("//table[@name='pps_access_keys']/row/data[@name='PARENT_ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_account_features']/row/data[@name='ACCOUNT_ID']");
		
		allIdCollections.add("//table[@name='pps_account_history']/row/data[@name='HISTORY_ID']");
		allIdCollections.add("//table[@name='pps_account_history']/row/data[@name='ACCOUNT_ID']");
		allIdCollections.add("//table[@name='pps_account_history']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_account_permissions']/row/data[@name='ACCOUNT_ID']");
		
		allIdCollections.add("//table[@name='pps_accounts']/row/data[@name='ACCOUNT_ID']");
		allIdCollections.add("//table[@name='pps_accounts']/row/data[@name='SALES_CONTACT_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_entries']/row/data[@name='ACL_ENTRY_ID']");
		allIdCollections.add("//table[@name='pps_acl_entries']/row/data[@name='ACL_ID']");
		allIdCollections.add("//table[@name='pps_acl_entries']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_fields']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_multi_fields']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_preferences']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_quotas']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_quotas_history']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_acl_rollup']/row/data[@name='ACL_ID']");
		allIdCollections.add("//table[@name='pps_acl_rollup']/row/data[@name='PARENT_ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_acls']/row/data[@name='PARENT_ACL_ID']");
		allIdCollections.add("//table[@name='pps_acls']/row/data[@name='OWNER_PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_action_principal_status']/row/data[@name='ACTION_ID']");
		allIdCollections.add("//table[@name='pps_action_principal_status']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_actions']/row/data[@name='TARGET_ACL_ID']");
		allIdCollections.add("//table[@name='pps_actions']/row/data[@name='ACTION_ID']");
		
		allIdCollections.add("//table[@name='pps_asset_annotations']/row/data[@name='ASSET_ID']");
		allIdCollections.add("//table[@name='pps_asset_annotations']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_asset_entries']/row/data[@name='ASSET_ID']");
		
		/*TODO Do we need this?*/
		//allIdCollections.add("//table[@name='pps_asset_interactions']/row/data[@name='SOURCE_INTERACTION_ID']");
		
		allIdCollections.add("//table[@name='pps_asset_responses']/row/data[@name='INTERACTION_ID']");
		
		allIdCollections.add("//table[@name='pps_fields']/row/data[@name='ACCOUNT_ID']");
		
		allIdCollections.add("//table[@name='pps_group_member_rollup']/row/data[@name='GROUP_ID']");
		allIdCollections.add("//table[@name='pps_group_member_rollup']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_group_members']/row/data[@name='GROUP_ID']");
		allIdCollections.add("//table[@name='pps_group_members']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_learning_path']/row/data[@name='CURRICULUM_ID']");
		allIdCollections.add("//table[@name='pps_learning_path']/row/data[@name='CURRENT_SCO_ID']");
		allIdCollections.add("//table[@name='pps_learning_path']/row/data[@name='TARGET_SCO_ID']");
		
		allIdCollections.add("//table[@name='pps_permission_activities']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_permission_activities']/row/data[@name='ASSET_ID']");
		allIdCollections.add("//table[@name='pps_permission_activities']/row/data[@name='PRINCIPAL_ID']");
		
		allIdCollections.add("//table[@name='pps_principals']/row/data[@name='PRINCIPAL_ID']");
		allIdCollections.add("//table[@name='pps_principals']/row/data[@name='ACCOUNT_ID']");
		
		allIdCollections.add("//table[@name='pps_report_descriptors']/row/data[@name='OWNER_ID']");
		
		allIdCollections.add("//table[@name='pps_sco_assets']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_sco_assets']/row/data[@name='ASSET_ID']");
		
		/*TODO: Do we need to add Question_id here*/
		allIdCollections.add("//table[@name='pps_sco_questions']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_sco_questions']/row/data[@name='INTERACTION_ID']");
		
		allIdCollections.add("//table[@name='pps_sco_receipts']/row/data[@name='USER_ID']");
		allIdCollections.add("//table[@name='pps_sco_receipts']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_sco_receipts']/row/data[@name='ECEIPT_ACTION_ID']");
		
		allIdCollections.add("//table[@name='pps_sco_shortcuts']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_sco_shortcuts']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_scos']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_scos']/row/data[@name='ACCOUNT_ID']");
		allIdCollections.add("//table[@name='pps_scos']/row/data[@name='FOLDER_ID']");
		allIdCollections.add("//table[@name='pps_scos']/row/data[@name='SOURCE_SCO_ID']");
		
		/*TODO: Do we need to add pps_threshold_exceeded.THRESHOLD_RECORD_ID here?*/
		allIdCollections.add("//table[@name='pps_threshold_exceeded']/row/data[@name='ACL_ID']");
		allIdCollections.add("//table[@name='pps_threshold_exceeded']/row/data[@name='SCO_ID']");
		
		allIdCollections.add("//table[@name='pps_transcript_counts']/row/data[@name='ACL_ID']");
		
		allIdCollections.add("//table[@name='pps_transcript_details']/row/data[@name='TRANSCRIPT_ID']");
		allIdCollections.add("//table[@name='pps_transcript_details']/row/data[@name='INTERACTION_ID']");
		
		/*TODO: Do we need to add AUDIT_ID here?*/
		allIdCollections.add("//table[@name='pps_transcripts']/row/data[@name='SCO_ID']");
		allIdCollections.add("//table[@name='pps_transcripts']/row/data[@name='ASSET_ID']");
		allIdCollections.add("//table[@name='pps_transcripts']/row/data[@name='PRINCIPAL_ID']");
		allIdCollections.add("//table[@name='pps_transcripts']/row/data[@name='SESSION_ID']");
		allIdCollections.add("//table[@name='pps_transcripts']/row/data[@name='DATA_FIELD_ID']");
		
		allIdCollections.add("//table[@name='pps_trees']/row/data[@name='TREE_ID']");
		allIdCollections.add("//table[@name='pps_trees']/row/data[@name='ACCOUNT_ID']");
		
		allIdCollections.add("//table[@name='pps_user_sessions']/row/data[@name='USER_ID']");
		
		allIdCollections.add("//table[@name='pps_users']/row/data[@name='USER_ID']");
	}
}
