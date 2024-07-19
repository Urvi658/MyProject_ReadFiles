@TestCase
Feature: NDC-FareFamilyPrice-Return_ADT

  @ScenarioCreation1G
  Scenario Outline: Pricing Comparision Return 1-ADT for Gen-1 and Gen-3
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with retn flight from <retnfltfrom> to <retnfltto> with retn date <retndays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with oAuthResourceInfo 'CD87751C-AD46-4EDB-9F53-7B0DE72D751E' and Accessgroup 'CD87751C-AD46-4EDB-9F53-7B0DE72D751E'
    And with domainlistenerchannelid '9d2c9380-23f7-4a87-849d-6e154c1462bc' and environment 'Test'
    And with ReservationResource_Identifier '1c878f0a-81d8-4fd2-873d-40e77feab8a1' and PCC 'XB7'
    And with 1 and 'ADT' passenger
    And with CoreAffinity as '1G' and upsells value '' and OffersToReturn '500'      
    And with HCAProfileId '' and BSPCode '' and AgencyCountryCode 'ES'
    When Invoke the Search request 'Search_FareFamily_OpenJawOrReturnTrip' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    
    
    When Invoke the Price request 'Price_MultiCity_EK_1PTC' with endpoint 'NDC-PRICE-EK' for 'GEN-1' Version
    When Invoke the Price request 'Price_MultiCity_EK_1PTC' with endpoint 'NDC-PRICE-EK' for 'GEN-3' Version
    Then Validate and Compare the responses for "Price" for both Versions

	 Examples: 
      | countrycode | citycode | iata     | deptfltfrom | deptfltto | deptdays | retnfltfrom | retnfltto | retndays | carrieropt | idm_carrier_list | currencycode | pseudocitycode | domainregion |
      | US          | BOS      | 02355662 | HKG         | DXB       |       30 | DXB         | HKG       |       35 | EK         | EK               | USD          | XB7            | zu2-dv       |
     

  