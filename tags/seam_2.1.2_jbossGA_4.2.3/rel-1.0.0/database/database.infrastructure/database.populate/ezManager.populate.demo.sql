-- insert the project ezHouse and all the corresponding tasks
-- user=Mike Well; password=Mike
INSERT INTO user_identity(user_identity_id, name, password, is_active) SELECT seq_user_identity.NEXTVAL, 'Mike Well', 'd6ac022931a66a2bcc244db91818ebec76ce5e18', 1 FROM dual;
-- user=Veronica Good; password=Veronica
INSERT INTO user_identity(user_identity_id, name, password, is_active) SELECT seq_user_identity.NEXTVAL, 'Veronica Good', '99de5b28b1201e270bcf3f552853f167095a66f0', 1 FROM dual;
-- user=Ready2Live House; password=House
INSERT INTO user_identity(user_identity_id, name, password, is_active) SELECT seq_user_identity.NEXTVAL, 'Ready2Live House', '7a022a2222e1a193793ddf244a313ef8e92ede6e', 1 FROM dual;
--user=Ready2Decorate House; password=Decorate
INSERT INTO user_identity(user_identity_id, name, password, is_active) SELECT seq_user_identity.NEXTVAL, 'Ready2Decorate House', 'a8b609dc49f0634bb482b623c73412d5bd713684', 1 FROM dual;
--user=Ready2Celebrate House; password=Celebrate
INSERT INTO user_identity(user_identity_id, name, password, is_active) SELECT seq_user_identity.NEXTVAL, 'Ready2Celebrate House', '9bd81a66353065637c80b354cbfd63068f927158', 1 FROM dual;

INSERT INTO base_status(status_id, name) SELECT seq_base_status.NEXTVAL, 'started' FROM dual;
INSERT INTO base_status(status_id, name) SELECT seq_base_status.NEXTVAL, 'finished' FROM dual;

INSERT INTO project (project_id, name, status_id, start_date, end_date) SELECT seq_project.NEXTVAL, 'My dreamed House', (SELECT status_id FROM base_status WHERE name = 'started'), SYSDATE, SYSDATE+365 FROM dual;

INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'find the place for the futur construction', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 0, SYSDATE+10, SYSDATE+40 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'purchase the place for the futur construction', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 1, SYSDATE+40, SYSDATE+50 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'draft the version version of the architecture', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 2, SYSDATE+10, SYSDATE+20 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'model the house with a 3D software', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 3, SYSDATE+30, SYSDATE+50 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'create the final architecture plan', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 4, SYSDATE+50, SYSDATE+70 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'identify the partner for building the house', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 5, SYSDATE+80, SYSDATE+100 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'ask the quotation for building the house', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 6, SYSDATE+100, SYSDATE+102 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'ask the quotation for indoor decoration', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 7, SYSDATE+100, SYSDATE+110 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'ask the quotation for outdoor decoration', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 8, SYSDATE+100, SYSDATE+110 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'start the construction', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'finished'), 9, SYSDATE+140, SYSDATE+270 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'start the indoor decoration', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'started'), 10, SYSDATE+270, SYSDATE+340 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'start the outdoor decoration', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'started'), 11, SYSDATE+330, SYSDATE+365 FROM dual;
INSERT INTO task (task_id, name, project_id, status_id, position, start_date, end_date) SELECT seq_task.NEXTVAL, 'organize a house warming party', (SELECT project_id FROM project WHERE name = 'My dreamed House'), (SELECT status_id FROM base_status WHERE name = 'started'), 12, SYSDATE+365, SYSDATE+365 FROM dual;


INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'find the place for the futur construction'), 'cost construction', 10, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 50 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'purchase the place for the futur construction'), 'cost for place purchase', 20000, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 60 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'draft the version version of the architecture'), 'cost for buying a 3D House software', 100, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 10 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'model the house with a 3D software'), 'cost for modeling', 100, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 150 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'create the final architecture plan'), 'cost for finalizing the plan', 1000, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 50 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'identify the partner for building the house'), 'travel cost', 50, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 5 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'ask the quotation for building the house'), 'phone cost', 10, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 5 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'ask the quotation for indoor decoration'), 'phone cost', 10, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 5 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'ask the quotation for outdoor decoration'), 'phone cost', 10, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 5 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'start the construction'), 'construction cost', 40000, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 5000 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'start the indoor decoration'), 'indoor decoration cost', 15000, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 200 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'start the outdoor decoration'), 'outdoor decoration cost', 10000, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 100 FROM dual;
INSERT INTO cost (cost_id, task_id, name, amount_proposed, vat_id, total_hours) SELECT seq_cost.NEXTVAL, (SELECT task_id FROM task WHERE name = 'organize a house warming party'), 'apero cost', 200, (SELECT vat_id FROM base_vat WHERE name ='French Vat'), 5 FROM dual;


INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Mike Well') FROM task WHERE name = 'find the place for the futur construction';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Mike Well') FROM task WHERE name = 'purchase the place for the futur construction';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Veronica Good') FROM task WHERE name = 'draft the version version of the architecture';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Veronica Good') FROM task WHERE name = 'model the house with a 3D software';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Mike Well') FROM task WHERE name = 'create the final architecture plan';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Veronica Good') FROM task WHERE name = 'identify the partner for building the house';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Mike Well') FROM task WHERE name = 'ask the quotation for building the house';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Mike Well') FROM task WHERE name = 'ask the quotation for indoor decoration';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Veronica Good') FROM task WHERE name = 'ask the quotation for outdoor decoration';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Ready2Live House') FROM task WHERE name = 'start the construction';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Ready2Decorate House') FROM task WHERE name = 'start the indoor decoration';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Ready2Decorate House') FROM task WHERE name = 'start the outdoor decoration';
INSERT INTO executed_task (executed_task_id, task_id, total_hours, amount, start_date, end_date, executed_by_user_id) SELECT seq_executed_task.NEXTVAL, task_id, 240, 0, start_date, end_date, (SELECT user_identity_id FROM user_identity WHERE name = 'Ready2Celebrate House') FROM task WHERE name = 'organize a house warming party';


COMMIT;