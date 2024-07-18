@TestCase
Feature: NDC-FareFamilySearch-OneWay_2PTC
  

  @ScenarioCreation1G @SkipTest
  Scenario Outline: FareFamilySearch OneWay 2-PTC for Gen-1 and Gen-3 responses
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with oAuthResourceInfo '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618' and Accessgroup '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618' 
    And with domainlistenerchannelid '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and environment 'Test'
    And with ReservationResource_Identifier '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and PCC '795E'
    And with 2 and 'ADT' passenger
    And with 1 and 'CHD' passenger
    And with CoreAffinity as '1G' and upsells value ''
    And Ignore below keywords or values while comparing Gen-1 and Gen-3 responses
      | id                |
      | BrandRef          |
      | FlightRef         |
     
    When Invoke the Search request 'Search_FareFamily_OneWay_2PTC' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    When Invoke the Search request 'Search_FareFamily_NextLeg' with endpoint 'NDC-Search-NextLeg' Request for 'GEN-1' Version
    When Invoke the Search request 'Search_FareFamily_OneWay_2PTC' with endpoint 'NDC-Search' Request for 'GEN-3' Version
    When Invoke the Search request 'Search_FareFamily_NextLeg' with endpoint 'NDC-Search-NextLeg' Request for 'GEN-3' Version
    
    Then Skip below tags while doing comparisions
    |"@type": "CatalogProductOfferingsResponse",|

    Then Remove tags from the response for below RequestType and Version
      | TagName   | RequestType               | Version |
      | NextSteps | Search_FareFamily         | GEN-1   |
      | Result    |                           |         |

    Then Remove tags from the response for below RequestType and Version
      | TagName   | RequestType               | Version |
      | NextSteps | Search_FareFamily         | GEN-3   |
      | Result    |                           |         |

    Then Remove tags from the response for below RequestType and Version
      | TagName   | RequestType               | Version |
      | NextSteps | Search_FareFamily_NextLeg | GEN-1   |
      | Result    |                           |         |

    Then Remove tags from the response for below RequestType and Version
      | TagName   | RequestType               | Version |
      | NextSteps | Search_FareFamily_NextLeg | GEN-3   |
      | Result    |                           |         |

    Then Validate and Compare the responses for "Search_FareFamily_OneWay_1PTC" both Versions
    Then Validate and Compare the responses for "Search_FareFamily_NextLeg" both Versions
    
    Examples: 
      | countrycode | citycode | iata     | deptfltfrom | deptfltto | deptdays | carrieropt | idm_carrier_list   | currencycode | pseudocitycode | domainregion |
      | SG          | BKK      | 32303843 | HKG         | BKK       |       30 | SQ         | SQ                 | USD          | 795E           | zu2-dv       |
