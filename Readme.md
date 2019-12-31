# ConnectALL Trello Adapter 

ConnectALL Trello Adapter is developed as an extension to the Universal adapter capability of ConnectALL. This adapter specifications will let the user use Trello with Webhooks to read and write the data in to Trello. 

Please refere https://wiki.connectall.com/ca/latest/user-guide/adapters/custom-application-adapter for more information


# How to use

## Import specifications
* Import *-adapter-descriptor.json in to ConnectALL using "Install custom adapter" feature. Customize the descriptor after importing to fit your personal boards and custom fields
* View the transformation specs and open the transformation spec page
* Choose the operation, adapter type and create draft specifications
* Copy the content of {adaptertype}-{operation}-[request|response].[groovy|jolt] in to the text area
* Test the specifications and save them as Active

## Define application links
* Create an application link in ConnectALL between Trello and a destination application of your choice
* Navigate to `Configuration -> Connections` screen and create a new connection to Trello using `https://api.trello.com` as the endpoint
* In the Entity mapping tab under Advanced Properties choose "Sync Type" as POLL
* In the WebHook Grid use the templates below for each operation

|Operation|API Method|Template|
|--- | --- | ---|
|QUERY MODIFIED RECORDS|GET|/1/boards/${board}?cards=all&card_members=true&card_member_fields=all&card_customFieldItems=true&cards_modifiedSince=${last-modified-time}&key=MyAPIKEY&token=MyAPIToken|
|READ RECORD BY ID|GET|/1/cards/${recordId}?fields=all&checkItemStates=false&checklists=all&checklist_fields=all&board=false&list=true&pluginData=false&customFieldItems=true&key=MyAPIKEY&token=MyAPIToken|
|CREATE RECORD|POST|/1/cards?idList=5dd3340ce51d2e1893b8ef7e&key=MyAPIKEY&token=MyAPIToken|
|UPDATE RECORD|PUT|/1/cards/${recordid}?&key=MyAPIKEY&token=MyAPIToken|

In order to use the trello adapter you will need to get the license from ConnectALL sales team. Please reach out to sales@connectall.com for licenses and quotes.

