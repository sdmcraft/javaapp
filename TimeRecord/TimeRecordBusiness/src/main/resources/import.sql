insert into tr_resources(id,resource_type,parent_id) values (-1,1,null);
insert into tr_principals(id,name) values (-1,'root');
insert into tr_acl(id,permissions,principal_id,resource_id) values (-1,3, -1, -1);