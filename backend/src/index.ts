import express from "express";
import dotenv from 'dotenv';
import { getUser, initGetAllUser, initGetUser } from "./api/user-management/getUser";
import * as managementRouter from './api/user-management/router';
import { createUser, initAddToGroup, initCreateUser } from "./api/user-management/createUser";
import { initOpenApi, openApiInstance } from "./openapi";
import { initialize } from "./initialize";
import { initCreateGroup } from "./api/group-management/createGroup";
import bodyParser from "body-parser";
import { initUpdateUser } from "./api/user-management/updateUser";

const SERVER_PORT = process.env.PORT || 8081

const app = express();
dotenv.config();

app.use(express.json());
app.use(bodyParser.json(), bodyParser.urlencoded({ extended: true }));
app.use('/api', managementRouter.default);

// User Management
initCreateUser(app, openApiInstance);
initGetAllUser(app, openApiInstance);
initGetUser(app, openApiInstance);
initUpdateUser(app, openApiInstance)

// Group Management
initCreateGroup(app, openApiInstance);
initAddToGroup(app, openApiInstance);
initOpenApi(app, openApiInstance);


// Testing
const listener = app.listen(SERVER_PORT, () => {
    console.log(listener.address())
    console.log(`Server running on port ${SERVER_PORT}`);
    console.log('To see Api documentation go to', `http://127.0.0.1:${SERVER_PORT}/docs`)
});
