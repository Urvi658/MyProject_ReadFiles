@TestCase
Feature: NDC-FareFamilySearch-MultiCity_1PTC
 

  @ScenarioCreation1G
  Scenario Outline: FareFamilySearch MultiCity 1-ADT for Gen-1 and Gen-3 responses
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with onward flt from <onwardfltfrom> to <onwardfltto> with onward flt date <onwrddays> days
    And with retn flight from <retnfltfrom> to <retnfltto> with retn date <retndays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with oAuthResourceInfo '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618' and Accessgroup '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618' 
    And with domainlistenerchannelid '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and environment 'Test'
    And with ReservationResource_Identifier '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and PCC '795E'
    And with 1 and 'ADT' passenger
    And with CoreAffinity as '1G' and upsells value ''
    #And Ignore below keywords or values while comparing Gen-1 and Gen-3 responses
    #| IgnoreDecimalZero |
    When Invoke the Search request 'Search_FareFamily_MultiCity_1PTC' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    When Invoke the Search request 'Search_FareFamily_NextLeg' with endpoint 'NDC-Search-NextLeg' Request for 'GEN-1' Version
    When Invoke the Search request 'Search_FareFamily_MultiCity_1PTC' with endpoint 'NDC-Search' Request for 'GEN-3' Version
    When Invoke the Search request 'Search_FareFamily_NextLeg' with endpoint 'NDC-Search-NextLeg' Request for 'GEN-3' Version
    Then Validate and Compare the responses for "Search_FareFamily_OneWay_1PTC" both Versions
    
  
    Examples: 
      | countrycode | citycode | iata      | deptfltfrom | deptfltto | deptdays | onwardfltfrom | onwardfltto | onwrddays |retnfltfrom | retnfltto | retndays | carrieropt | idm_carrier_list 			| currencycode | pseudocitycode | domainregion |
      | SG          | BKK      | 32303843  | SIN         | BKK       |       30 | BKK           | HKG         |       40  |HKG         | SIN       |       60 | SQ         | SQ                     | USD          | 795E            | zu2-dv       |
    