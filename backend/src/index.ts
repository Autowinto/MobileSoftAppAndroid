import express from "express";
import dotenv from 'dotenv';
import { getUser, initGetUser } from "./api/user-management/getUser";
import * as managementRouter from './api/user-management/router';
import { initCreateUser } from "./api/user-management/createUser";
import { initOpenApi, openApiInstance } from "./openapi";
import * as db from "./db/dbService";

const app = express();
dotenv.config();

app.use(express.json());

app.use('/api', managementRouter.default);

initCreateUser(app, openApiInstance);
initGetUser(app, openApiInstance);
initOpenApi(app, openApiInstance);

db.run();
db.get();

// Testing
app.listen(process.env.PORT, () => {
    console.log(`Server running on port ${process.env.PORT}`);
    console.log('To see Api documentation go to', `http://${process.env.HOST}:${process.env.PORT}/docs`)
});
