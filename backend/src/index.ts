import express from "express";
import dotenv from 'dotenv';
import { getUser, initGetAllUser, initGetUser } from "./api/user-management/getUser";
import * as managementRouter from './api/user-management/router';
import { createUser, initAddToGroup, initCreateUser } from "./api/user-management/createUser";
import { initOpenApi, openApiInstance } from "./openapi";
import { initialize } from "./initialize";
import { initCreateGroup } from "./api/group-management/createGroup";

const app = express();
dotenv.config();

app.use(express.json());

app.use('/api', managementRouter.default);

initCreateUser(app, openApiInstance);
initGetAllUser(app, openApiInstance);
initGetUser(app, openApiInstance);
initCreateGroup(app, openApiInstance);
initAddToGroup(app, openApiInstance);
initOpenApi(app, openApiInstance);


// Testing
app.listen(process.env.PORT, () => {
    initialize();
    console.log(`Server running on port ${process.env.PORT}`);
    console.log('To see Api documentation go to', `http://${process.env.HOST}:${process.env.PORT}/docs`)
});
