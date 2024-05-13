import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class DealershipTest extends CollectionTestBase {




	@Override
	protected String getClassToCheck() {
		return "Dealership";
	}

	public void test_DealershipConstructorTest() throws VizException {
		setConfigKeyRelevantClasses("Dealership, BrandSet, SinglyLinkedListBrandNode, Brand, VendorSet, SinglyLinkedListVendorNode, Vendor, CarSet, SinglyLinkedListCarNode, Car, CarVendorMap, SinglyLinkedListCarVendor");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(7);
		setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("Dealership:2, BrandSet:3, SinglyLinkedListBrandNode:5, Brand:5, VendorSet:3, SinglyLinkedListVendorNode:5, Vendor:5, CarSet:3, SinglyLinkedListCarNode:5, Car:5, CarVendorMap:3, SinglyLinkedListCarVendor:5");
		check(GENERIC_PROPERTIES,"Dealership(BrandSet,VendorSet, CarSet)", false);
	}

	public void test_sellTest() throws VizException {
		setConfigKeyRelevantClasses("Dealership, BrandSet, SinglyLinkedListBrandNode, Brand, VendorSet, SinglyLinkedListVendorNode, Vendor, CarSet, SinglyLinkedListCarNode, Car, CarVendorMap, SinglyLinkedListCarVendor");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(7);
		setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("Dealership:10, BrandSet:10, SinglyLinkedListBrandNode:10, Brand:10, VendorSet:10, SinglyLinkedListVendorNode:10, Vendor:10, CarSet:10, SinglyLinkedListCarNode:10, Car:10, CarVendorMap:10, SinglyLinkedListCarVendor:10");
		check(GENERIC_PROPERTIES,"sell(Car,Vendor)", false);
	}


	public void test_buyTest() throws VizException {
		setConfigKeyRelevantClasses("Dealership, BrandSet, SinglyLinkedListBrandNode, Brand, VendorSet, SinglyLinkedListVendorNode, Vendor, CarSet, SinglyLinkedListCarNode, Car, CarVendorMap, SinglyLinkedListCarVendor");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(7);
		setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("Dealership:10, BrandSet:10, SinglyLinkedListBrandNode:10, Brand:10, VendorSet:10, SinglyLinkedListVendorNode:10, Vendor:10, CarSet:10, SinglyLinkedListCarNode:10, Car:10, CarVendorMap:10, SinglyLinkedListCarVendor:10");
		check(GENERIC_PROPERTIES,"buy(Car)", false);
	}

}
