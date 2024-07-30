@TestCase
Feature: NDC-Price-Return_1ADT1CHD1INF

  @ScenarioCreation1G
  Scenario Outline: Pricing Comparision Return 3-PTC for Gen-1 and Gen-3
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with retn flight from <retnfltfrom> to <retnfltto> with retn date <retndays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with oAuthResourceInfo 'CD87751C-AD46-4EDB-9F53-7B0DE72D751E' and Accessgroup 'CD87751C-AD46-4EDB-9F53-7B0DE72D751E'
    And with domainlistenerchannelid '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and environment 'Test'
    And with ReservationResource_Identifier '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and PCC 'XB7'
    And with 1 and 'ADT' passenger
    And with 1 and 'CHD' passenger
    And with 1 and 'INF' passenger
    And with CoreAffinity as '1G' and upsells value '4' and OffersToReturn '50'      
    And with HCAProfileId 'ODTS_1G_680I_B58AEF' and BSPCode '016' and AgencyCountryCode 'AU' 
    When Invoke the Search request 'Search_FareFamily_OpenJawOrReturnTrip' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    When Invoke the Price request 'Price_MultiCity_QF' with endpoint 'NDC-PRICE-EK' for 'GEN-1' Version
    When Invoke the Price request 'Price_MultiCity_QF' with endpoint 'NDC-PRICE-EK' for 'GEN-3' Version
    Then Validate and Compare the responses for "Price" for both Versions

	 Examples: 
      | countrycode | citycode | iata      | deptfltfrom | deptfltto | deptdays | retnfltfrom | retnfltto | retndays | carrieropt | idm_carrier_list | currencycode | pseudocitycode | domainregion |
      | AU          | SYD      | 02345243  | SYD         | SIN       |       30 | SIN         | SYD       |       35 | QF         | QF               | USD          | XB7            | zu2-dv       |
     

  