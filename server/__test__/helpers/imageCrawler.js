/* eslint-disable no-await-in-loop */
// eslint-disable-next-line import/no-extraneous-dependencies
import puppeteer from "puppeteer-core";
import path from "path";

const crawlImages = async (downloadDir) => {
    const baseURL = "https://www.pexels.com/";
    const chromiumPath = "/usr/bin/chromium";
    const browser = await puppeteer.launch({
        executablePath: chromiumPath,
        headless: false,
    });
    const page = await browser.newPage();
    page.setDefaultNavigationTimeout(1000000);
    console.log("Wait for load for some minutes");
    console.log("Loading......");
    await page.goto(baseURL, {});
    await page.waitForSelector("a[download=true][href*=photo]");
    await page._client.send("Page.setDownloadBehavior", {
        behavior: "allow",
        downloadPath: downloadDir,
    });
    const imageLinks = await page.$$("a[class*=item__link][href*=photo]");
    console.log("Target image count: ", imageLinks.length);
    console.log("Wait for download");
    console.log("Downloading......");
    let count = 0;
    for (const imageLink of imageLinks) {
        await imageLink.click();
        await page.waitForSelector("a[download][target][class*= button]");
        const downloadLink = await page.$("a[download][target][class*= button]"); 
        await downloadLink.click();
        await page.goBack();
        count++;
        if (count === imageLinks.length) {
            console.log("Done");
            await page.waitFor(2000);
            await browser.close();
        }
    }
};

crawlImages(path.join(__dirname, "..", "acceptanceTest", "testFiles"));
