class CAResponse {
  int totalRecords;
  List<CAResponseResult> result;
}
class CAResponseResult {
  String id;
  Map fields;
}

def jsonS = new groovy.json.JsonSlurper()
def appResponse = jsonS.parseText(payload) as Map

def _response = new CAResponse();
_response.totalRecords = appResponse.cards.size();
_resultArray = new ArrayList();
for(def card:appResponse.cards) {
  def _record = new CAResponseResult();
  _record.id = card.id;
  def _fields = new HashMap();
  
  // Copy fields
  for(Map.Entry<String,Object> cardField:card.entrySet()) {
    if(cardField.value instanceof String) {
      _fields.put(cardField.key,cardField.value)
    }
  }

  // Copy custom fields
  for(def cfield:card.customFieldItems) {
    if(cfield.idValue!=null) {
      _fields.put(cfield.idCustomField,cfield.idValue)
    } else if(cfield.value != null && cfield.value instanceof Map) {
      if(cfield.value.containsKey("text"))
        _fields.put(cfield.idCustomField,cfield.value.text)
      else if(cfield.value.containsKey("number"))
        _fields.put(cfield.idCustomField,cfield.value.number)
      else if(cfield.value.containsKey("date"))
        _fields.put(cfield.idCustomField,cfield.value.date)
      else if(cfield.value.containsKey("checked"))
        _fields.put(cfield.idCustomField,cfield.value.checked)
      }
    }
  _record.fields = _fields;
  _resultArray.add(_record);
  }
_response.result = _resultArray




groovy.json.JsonOutput.toJson(_response)