insert into tr_resources(id,resource_type,parent_id) values (-1,0,null);
insert into tr_principals(id,name) values (-1,'root');
insert into tr_users(id,image,name,password,principal_id) values(-1,null,'root','root',-1);
insert into tr_principal_group_members(member_id,group_id) values (-1,-1);
insert into tr_acl(id,permissions,principal_id,resource_id) values (-1,3, -1, -1);