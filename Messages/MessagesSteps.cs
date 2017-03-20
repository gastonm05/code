using CCC_API.Steps.Common;
using BoDi;
using TechTalk.SpecFlow;
using RestSharp;
using CCC_API.Data.Responses.Messages;
using NUnit.Framework;
using CCC_Infrastructure.Utils;
using CCC_API.Services.Messages;

namespace CCC_API.Steps.Messages
{
    [Binding]
    public class MessagesSteps : AuthApiSteps
    {
        public MessagesSteps(IObjectContainer objectContainer) : base(objectContainer) { }

        [When(@"I publish a message to (.*) with a text length of (.*), (.*), shorten url service (.*) and (.*) as expected response code")]
        public void WhenIPublishAMessageToWithAAnd(string platform, int textLength, string imageUrl, bool shortenUrls, string responseCode)
        {
            // SocialMediaPublishing config is set as false by default to preserve QA sources from reaching post rate limits of external social media platforms
            if (TestSettings.GetConfigValue("SocialMediaPublishing") == "true")
            {
                var messageService = new MessageService(SessionKey);
                var socialMediaList = messageService.PlatformAsProperty(platform);
                var stringLength = messageService.GetRandomString(textLength);

                IRestResponse response = messageService.PostMessage(socialMediaList, stringLength, imageUrl, shortenUrls);
                Assert.AreEqual(responseCode.Replace("\"", ""), response.StatusCode.ToString(), "Wrong Status code on the response");
                PropertyBucket.Remember("response", response);
            }
        }
    }
}

