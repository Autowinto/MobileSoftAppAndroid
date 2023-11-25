import { initialize } from "./initialize";


export async function main() {
    await initialize();

    const { appInit } = await import('./app-init');
    await appInit();
}

main();
