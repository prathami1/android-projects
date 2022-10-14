use priority queue to sort the periodic table elements within listView (instead of arrayList)

use classes for element (create an object and call objects to the listView). ✅

make your own API with images to each element in the periodic table (half, still need to add images to API)

put this in xml: 
```xml
android:background="#F586FF29"
```

## Things to do (in semi-chronological order):
- [X] add more info to horizontal layout 
- [X] find API (or make own)
- [X] customize API if not enough info is needed
- [X] implement API within app and sort into arrayLists
- [X] get all the data to render in the listView first
- [X] add more parameters to the class file (element) so that horizontal parameters can be met
- [X] work on remove element (search up how to edit list view GUI)
- [X] find a way to save state for rotation
- [ ] instead of arrayList convert to priority queue to flex sorting of listView (maybe prioritize metals, atomic number, etc) with buttons on the app
- [ ] add the buttons on a linear layout on the top of the listVIew and adjust the XML accordingly
- [ ] make a detailed view to click on only when in portrait mode rather than landscape (landscape view already has all the information)
- [ ] add DetailedActivity.java and activity_detailed.xml

## old API java code
```java
class ElementInfo extends AsyncTask<String, Void, ArrayList<Element>>
    {
        private ProgressDialog dialog;

        public ElementInfo(MainActivity mainActivity)
        { dialog = new ProgressDialog(mainActivity); }

        @Override
        protected ArrayList<Element> doInBackground(String... elementInfo) {

            HttpURLConnection httpURLConnection = null;
            InputStream contentStream = null;
            Scanner scanner = null;

            ArrayList<Element> elements = new ArrayList<>();
            try
            {
                String apiUrl = "https://raw.githubusercontent.com/prathami1/data/main/periodic-table/data.json";

                URL url = new URL(apiUrl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                contentStream = (InputStream) httpURLConnection.getContent();
                scanner = new Scanner(contentStream);
                scanner.useDelimiter("\\A");

                String elementInfoJSONStr = "";
                if(scanner.hasNext())
                { elementInfoJSONStr = scanner.next(); }
                else
                { scanner.next(); }

                JSONObject elementInfoJSONObject = new JSONObject(elementInfoJSONStr);
                JSONArray elementInfoJSONArray = elementInfoJSONObject.getJSONArray("elements");

                for(int i = 0; i < elementInfoJSONArray.length(); i++)
                {

                }

            }
            catch (JSONException | IOException e)
            { Log.d("LOG", e.toString()); }
            finally
            {
                try
                {
                    if(scanner != null)
                    { scanner.close(); }
                    if(contentStream != null)
                    { contentStream.close(); }
                }
                catch (IOException e)
                { Log.d("LOG", e.toString()); }
                if(httpURLConnection != null)
                { httpURLConnection.disconnect(); }
            }
            //return element here
            return elements;
        }
    }
```
